package com.speedstersreborn.util.handlers;


import com.revivalcore.util.helper.PlayerHelper;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber
public class EventHandlePower {

    @SubscribeEvent
    public static void MainPowers(LivingEvent.LivingUpdateEvent e) {
        setXPAdd(e);
        runWater(e);
    }


    public static void setXPAdd(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (!player.world.isRemote) {
                if (cap.isSpeedster() && !player.capabilities.isCreativeMode) {
                    if (isMoving(player)) {
                        cap.setXP(cap.getXP() + 0.01 * cap.getSpeedLevel());
                        PlayerHelper.sendMessage(player, "XP: " + cap.getXP(), true);
                    }


                    if (cap.getXP() >= 100 && cap.getXP() < 100.5) {
                        cap.setLevel(2);
                        PlayerHelper.sendMessage(player, "Level: " + cap.getLevel(), false);
                    }

                    if (cap.getXP() >= 200 && cap.getXP() < 200.5) {
                        cap.setLevel(3);
                        PlayerHelper.sendMessage(player, "Level: " + cap.getLevel(), false);
                    }

                    if (cap.getXP() >= 300 && cap.getXP() < 300.5) {
                        cap.setLevel(4);
                        PlayerHelper.sendMessage(player, "Level: " + cap.getLevel(), false);
                    }

                    if (cap.getXP() >= 400 && cap.getXP() < 400.5) {
                        cap.setLevel(5);
                        PlayerHelper.sendMessage(player, "Level: " + cap.getLevel(), false);
                    }
                }
            }
        }
    }


    public static void runWater(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (player.isSprinting() && cap.isSpeedster() && player.world.getBlockState(player.getPosition().add(0, -1, 0)).getBlock() instanceof BlockLiquid) {
                {
                    player.posY -= player.motionY;
                    player.motionY = 0D;
                    player.fallDistance = 0.0F;
                    player.onGround = true;
                }
            }
        }
    }

    public static boolean isMoving(EntityLivingBase entity) {
        return (entity.distanceWalkedModified != entity.prevDistanceWalkedModified);
    }
}
