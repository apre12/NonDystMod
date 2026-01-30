package me.hakotsuki2003.nondyst.nondystmod;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.rmi.registry.Registry;


public class ExampleThrowableItem extends Item {
    public ExampleThrowableItem(Properties properties) {
        super(properties);

    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        //クールダウン
        player.getCooldowns().addCooldown(this,1);

        if(!level.isClientSide){
            //ここが投げられる側
            ExampleThrownItem entity = new ExampleThrownItem(player,level);
            entity.setNoGravity(true);
            //投げられる物の見た目の設定
            entity.setItem(new ItemStack(Nondystmod.MICROWAVE_FREQUENCY.get()));

            entity.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0F,
                    5.0F,
                    1.0F

            );
            level.addFreshEntity(entity);
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    SoundEvents.ENDER_PEARL_THROW,
                    SoundSource.NEUTRAL,
                    0.5F,
                    0.4F / (level.getRandom().nextFloat()*0.4F+0.8F
                    )
            );
        }


        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}
