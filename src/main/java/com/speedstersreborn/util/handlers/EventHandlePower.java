package com.speedstersreborn.util.handlers;


import com.revivalcore.common.capabilities.CapabilitySpeedster;
import com.revivalcore.common.capabilities.ISpeedsterCap;
import com.revivalcore.util.helper.PlayerHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandlePower {

  /*  @SubscribeEvent
    public static void Trail(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            if (cap.isSpeedster()) {
                if (player.posX != player.prevPosX || player.posZ != player.prevPosZ) {
                    player.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY + 0.5, player.posZ, 1.0, 1.0, 1.0);
                }
            }
        }
    }*/

    @SubscribeEvent
    public static void setXPAdd(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (!player.world.isRemote) {
                if (cap.isSpeedster() && !player.capabilities.isCreativeMode) {
                    BlockPos pos = new BlockPos(player.posX, player.posY - 1, player.posZ);
                    if (isMoving(player)) {
                        cap.setXP(cap.getXP() + 0.1 * cap.getSpeedLevel());
                    PlayerHelper.sendMessage(player, "XP: " + cap.getXP(), true);
                }


                if (cap.getXP() >= 100 && cap.getXP() < 101) {
                    cap.setLevel(2);
                    PlayerHelper.sendMessage(player, "Level: " + cap.getLevel(), false);
                }

                if (cap.getXP() >= 200 && cap.getXP() < 201) {
                    cap.setLevel(3);
                    PlayerHelper.sendMessage(player, "Level: " + cap.getLevel(), false);
                }

                if (cap.getXP() >= 300 && cap.getXP() < 301) {
                    cap.setLevel(4);
                    PlayerHelper.sendMessage(player, "Level: " + cap.getLevel(), false);
                }

                if (cap.getXP() >= 400 && cap.getXP() < 401) {
                    cap.setLevel(5);
                    PlayerHelper.sendMessage(player, "Level: " + cap.getLevel(), false);
                }
            }
        }
    }

}


    public static boolean isMoving(EntityPlayer player) {
        boolean result = false;

        if (player.motionX != 0 || player.motionZ != 0) { // TODO Fix that you have to jump to level up...
            result = true;
        }
        return result;
    }
}
