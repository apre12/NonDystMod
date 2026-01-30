package me.hakotsuki2003.nondyst.nondystmod;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class ExampleThrownItem extends ThrowableItemProjectile {

    public ExampleThrownItem(EntityType<? extends ThrowableItemProjectile>
                                     type, Level level) {
        super(type, level);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return Nondystmod.MICROWAVE_FREQUENCY.get();
    }

    @Override
    public void tick() {
        super.tick();
        Level level = this.level();
        int tickCount = this.tickCount;
        Vec3 motion = this.getDeltaMovement();
        this.setDeltaMovement(motion.scale(0.9));

    }

    @Override
    public ItemStack getPickedResult(HitResult target) {
        return super.getPickedResult(target);
    }

    public ExampleThrownItem(LivingEntity livingEntity, Level level) {
        super(Nondystmod.EXAMPLE_THROWN_ITEM.get(), livingEntity, level);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        Level level = this.level();
        if (!level.isClientSide()){
            //当たった時の効果
            level.explode(
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    1.0F,
                    Level.ExplosionInteraction.BLOCK

            );
            this.discard();
        }
    }
}
