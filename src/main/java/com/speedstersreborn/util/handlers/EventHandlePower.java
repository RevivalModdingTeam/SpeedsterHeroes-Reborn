package com.speedstersreborn.util.handlers;

import com.revivalcore.util.helper.PlayerHelper;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Josia50
 * on 10/04/2019.
 */

@Mod.EventBusSubscriber
public class EventHandlePower {

    @SubscribeEvent
    public static void MainPowers(LivingEvent.LivingUpdateEvent e) {
        setXPAdd(e);
        runWater(e);
        runWall(e);
        runAbilities(e);
        Phasing(e);
    }


    public static void setXPAdd(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (!player.world.isRemote) {
                if (cap.isSpeedster() && !player.capabilities.isCreativeMode) {
                    if (isMoving(player)) {
                        cap.setXP(cap.getXP() + 0.01 * cap.getSpeedLevel());
                        player.spawnRunningParticles();
                        player.addExhaustion(0.001f);
                        PlayerHelper.sendMessage(player, "XP: " + cap.getXP(), true);
                    }

                    if (cap.getXP() >= 100 && cap.getXP() < 100.5)
                        cap.setLevel(2);

                    if (cap.getXP() >= 200 && cap.getXP() < 200.5)
                        cap.setLevel(3);

                    if (cap.getXP() >= 300 && cap.getXP() < 300.5)
                        cap.setLevel(4);

                    if (cap.getXP() >= 400 && cap.getXP() < 400.5)
                        cap.setLevel(5);
                }
            }
        }
    }

    public static void runWater(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (player.isSprinting() && cap.isSpeedster() && cap.getSpeedLevel() >= 1 && player.world.getBlockState(player.getPosition().add(0, -1, 0)).getBlock() instanceof BlockLiquid) {
                player.posY -= player.motionY;
                player.motionY = 0D;
                player.fallDistance = 0.0F;
                player.onGround = true;
            }
        }
    }

    public static void runWall(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (cap.isSpeedster()) {
                if (player.collidedHorizontally) {
                    if (cap.isWallRunning()) {
                        if (cap.getSpeedLevel() >= 3) {
                            player.motionY = 0.8D;
                            player.fallDistance = 0F;
                            cap.setWallRunning(true);
                        }
                    }
                }
            }
        }
    }

    public static void Phasing(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (cap.isPhasing() && player.world.isBlockFullCube(new BlockPos(player.posX, player.posY - 0.1F, player.posZ))) { // TODO , Add check if you actually have speedster powers
                player.noClip = true;
                player.motionY = 0;
                player.onGround = true;
            }
        }
    }

    public static void runAbilities(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            if (cap.isSpeedster() && cap.getSpeedLevel() >= 1) {
                player.stepHeight = 1.3f;
                player.sendPlayerAbilities();
            }
        }
    }

    public static boolean isMoving(EntityLivingBase entity) {
        return (entity.distanceWalkedModified != entity.prevDistanceWalkedModified);
    }
}
