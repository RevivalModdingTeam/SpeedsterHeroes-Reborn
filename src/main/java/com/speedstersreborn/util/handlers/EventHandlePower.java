package com.speedstersreborn.util.handlers;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.revivalmodding.revivalcore.util.helper.ModHelper;
import com.revivalmodding.revivalcore.util.helper.PlayerHelper;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.block.BlockLiquid;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

/**
 * Created by Josia50
 * on 10/04/2019.
 */

@Mod.EventBusSubscriber
public class EventHandlePower {

    @SubscribeEvent
    public static void mainPowers(TickEvent.PlayerTickEvent e) {
        EntityPlayer player = e.player;
        ISpeedsterCap cap = CapabilitySpeedster.get(player);
        IMetaCap capmeta = CapabilityMeta.get(player);

        if (MetaHelper.getMetaPowerName(capmeta.getMetaPower()) == MetaPowerStrings.SPEEDSTER) {
            setXPAdd(player, cap);
            runWater(player, cap);
            runWall(player, cap);
            runAbilities(player, cap);
            phasing(player, cap);
            whileRunning(player, cap, capmeta);
        }
    }

    public static void setXPAdd(EntityPlayer player, ISpeedsterCap cap) {
        if (!player.world.isRemote) {
            if (cap.isSpeedster() && !player.capabilities.isCreativeMode) {
                if (isMoving(player)) {
                    cap.setXP(cap.getXP() + 0.01 * cap.getSpeedLevel());
                    player.spawnRunningParticles();
                    if(ModHelper.getIsDev())
                    	PlayerHelper.sendMessage(player, "XP: " + cap.getXP(), true);
                }
                updateLevel(cap);
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
            player.sendPlayerAbilities();
        }
    }

    public static void runAbilities(EntityPlayer player, ISpeedsterCap cap) {
        if (cap.isSpeedster() && cap.getSpeedLevel() >= 1) {
            player.stepHeight = 1.3f;
            player.sendPlayerAbilities();
        }

        if(EventHandlePower.isMoving(player) && cap.isSpeedster() && cap.getSpeedLevel() >= 6) {
            BlockPos blockPos = new BlockPos(player.posX, player.posY - 1, player.posZ);
            if(player.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN) {
                player.world.setBlockState(player.getPosition(), Blocks.FIRE.getDefaultState());
            }
        }
    }

    public static void whileRunning(EntityPlayer player, ISpeedsterCap cap, IMetaCap capmeta) {
        if (!player.world.isRemote && cap.isSpeedster() && cap.getSpeedLevel() > 1 && isMoving(player)) {
            if (player.isBurning()) {
                player.extinguish();
            }
            FoodStats food = player.getFoodStats();
            food.setFoodSaturationLevel(food.getSaturationLevel() - 0.05f * cap.getSpeedLevel());
            if(food.getSaturationLevel() < 0f && food.getFoodLevel() > 0) {
            	food.setFoodSaturationLevel(20.0F);
            	food.setFoodLevel(food.getFoodLevel() - 1);
            }
            if (food.getFoodLevel() <= 1) {
                player.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 2, false, true));
            }
        }
        if (player.getHealth() > player.getMaxHealth()) {
            if (!player.getFoodStats().needFood()) {
                player.shouldHeal();
                player.setHealth(player.getHealth() + 1.0f);
            }
        }
    }

    public static boolean isMoving(EntityLivingBase entity) {
        return (entity.distanceWalkedModified != entity.prevDistanceWalkedModified);
    }
    
    private static void updateLevel(ISpeedsterCap cap) {
        	final double xp = cap.getXP();
        	final double required = (cap.getLevel()+1) * 100 + 100.0D;
        	if(xp >= required)
        		cap.setLevel(cap.getLevel() + 1);
    }
}
