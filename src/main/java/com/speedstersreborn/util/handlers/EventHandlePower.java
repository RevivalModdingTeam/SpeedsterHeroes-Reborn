package com.speedstersreborn.util.handlers;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
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
    public static void mainPowers(LivingEvent.LivingUpdateEvent e) {
        if (e.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) e.getEntity();

            IMetaCap capmeta = CapabilityMeta.get(player);

            if (MetaHelper.getMetaPowerName(capmeta.getMetaPower()) == MetaPowerStrings.SPEEDSTER) {
                ISpeedsterCap cap = CapabilitySpeedster.get(player);

                setXPAdd(player, cap);
                runWater(player, cap);
                runWall(player, cap);
                runAbilities(player, cap);
                phasing(player, cap);
                whileRunning(player, cap, capmeta);
            }
        }
    }


    public static void setXPAdd(EntityPlayer player, ISpeedsterCap cap) {
        if (!player.world.isRemote) {
            if (cap.isSpeedster() && !player.capabilities.isCreativeMode) {
                if (isMoving(player)) {
                    cap.setXP(cap.getXP() + 0.01 * cap.getSpeedLevel());
                    player.spawnRunningParticles();
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

    public static void runWater(EntityPlayer player, ISpeedsterCap cap) {
        if (player.isSprinting() && cap.isSpeedster() && cap.getSpeedLevel() >= 1 && player.world.getBlockState(player.getPosition().add(0, -1, 0)).getBlock() instanceof BlockLiquid) {
            player.posY -= player.motionY;
            player.motionY = 0D;
            player.fallDistance = 0.0F;
            player.onGround = true;
        }
    }

    public static void runWall(EntityPlayer player, ISpeedsterCap cap) {
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

    public static void phasing(EntityPlayer player, ISpeedsterCap cap) {
        if (cap.isPhasing() && player.world.isBlockFullCube(new BlockPos(player.posX, player.posY - 0.1F, player.posZ))) {
            player.noClip = true;
            player.motionY = 0;
            player.onGround = true;
        }
    }

    public static void runAbilities(EntityPlayer player, ISpeedsterCap cap) {
        if (cap.isSpeedster() && cap.getSpeedLevel() >= 1) {
            player.stepHeight = 1.3f;
            player.sendPlayerAbilities();
        }
    }


    public static void whileRunning(EntityPlayer player, ISpeedsterCap cap, IMetaCap capmeta) {
        if (cap.isSpeedster() && cap.getSpeedLevel() > 1 && isMoving(player)) {
            if (player.isBurning()) {
                player.extinguish();
            }
             player.getFoodStats().addExhaustion(0.010f);
            if (player.getFoodStats().getFoodLevel() <= 1) {
                player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 2));
            }
        }
        if (player.getHealth() < player.getMaxHealth()) {
            if (!(player.getFoodStats().getFoodLevel() < 6)) {
                player.shouldHeal();
                player.setHealth(player.getHealth() + 0.025f);
            }
        }
    }

    public static boolean isMoving(EntityLivingBase entity) {
        return (entity.distanceWalkedModified != entity.prevDistanceWalkedModified);
    }
}
