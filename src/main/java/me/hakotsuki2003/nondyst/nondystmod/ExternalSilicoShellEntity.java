package me.hakotsuki2003.nondyst.nondystmod;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

public class ExternalSilicoShellEntity extends TamableAnimal implements ItemSupplier {
    private static final EntityDataAccessor<Integer> SHELL_COLOR = SynchedEntityData.defineId(ExternalSilicoShellEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> ARCHAEA_ENERGY = SynchedEntityData.defineId(ExternalSilicoShellEntity.class, EntityDataSerializers.FLOAT);

    private final ItemStackHandler floopyInventory = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            // TODO: Update abilities based on installed floopies
        }
    };

    public ExternalSilicoShellEntity(EntityType<? extends TamableAnimal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SHELL_COLOR, 0xFFFFFF);
        this.entityData.define(ARCHAEA_ENERGY, 100.0F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return TamableAnimal.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.MAX_HEALTH, 40.0D) // Higher health for ESS
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ARMOR, 10.0D)      // Natural armor for the shell
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
    }

    public int getShellColor() {
        return this.entityData.get(SHELL_COLOR);
    }

    public void setShellColor(int color) {
        this.entityData.set(SHELL_COLOR, color);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        
        if (this.isTame() && this.isOwnedBy(player)) {
            // Floopy installation logic
            if (isFloopy(itemstack)) {
                if (!this.level().isClientSide) {
                    for (int i = 0; i < floopyInventory.getSlots(); i++) {
                        if (floopyInventory.getStackInSlot(i).isEmpty()) {
                            ItemStack toInsert = itemstack.copy();
                            toInsert.setCount(1);
                            floopyInventory.setStackInSlot(i, toInsert);
                            if (!player.getAbilities().instabuild) {
                                itemstack.shrink(1);
                            }
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            
            // Silica-fication placeholder
            if (itemstack.is(Nondystmod.SILICA_PLACEHOLDER.get())) {
                // TODO: Implement silica-fication logic
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

            if (itemstack.isEmpty()) {
                if (!this.level().isClientSide) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        } else if (itemstack.is(Items.QUARTZ)) {
            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            } else {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    this.setOrderedToSit(true);
                    this.level().broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte)6);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return super.mobInteract(player, hand);
    }

    private boolean isFloopy(ItemStack stack) {
        return stack.is(Nondystmod.GESTICK.get()) || 
               stack.is(Nondystmod.CHAP.get()) || 
               stack.is(Nondystmod.SPEEKER.get()) || 
               stack.is(Nondystmod.POND.get());
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("ShellColor", this.getShellColor());
        compound.putFloat("ArchaeaEnergy", this.entityData.get(ARCHAEA_ENERGY));
        compound.put("FloopyInventory", floopyInventory.serializeNBT());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("ShellColor")) {
            this.setShellColor(compound.getInt("ShellColor"));
        }
        if (compound.contains("ArchaeaEnergy")) {
            this.entityData.set(ARCHAEA_ENERGY, compound.getFloat("ArchaeaEnergy"));
        }
        if (compound.contains("FloopyInventory")) {
            floopyInventory.deserializeNBT(compound.getCompound("FloopyInventory"));
        }
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(Items.PAPER);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
