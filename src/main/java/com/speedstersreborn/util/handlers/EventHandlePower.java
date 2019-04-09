package com.speedstersreborn.util.handlers;


import com.revivalcore.util.helper.PlayerHelper;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber
public class EventHandlePower {

    @SubscribeEvent
    public static void setXPAdd(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (!player.world.isRemote) {
                if (cap.isSpeedster() && !player.capabilities.isCreativeMode) {
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

    public static boolean isMoving(EntityLivingBase entity) {
        return (entity.distanceWalkedModified / 0.6F != entity.prevDistanceWalkedModified / 0.6F);
    }
}
