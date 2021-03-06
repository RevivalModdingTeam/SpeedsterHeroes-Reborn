package com.speedstersreborn.api;

import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.config.SHRConfig;
import com.speedstersreborn.util.helper.MathHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

/**
 * Created by Josia50
 * on 5/04/2019.
 */

public class SpeedAPI {

    public static int MaxSpeedLevel = 20;

    public static void setSpeed(EntityPlayer player, int level) {
        // 2.5F for level 20 (with default config value)
        float speed = level * SHRConfig.speedstersHeroesReborn.speedIncreaseOverLevel;
        AttributeModifier mod = new AttributeModifier(UUID.fromString("42b68862-2bdc-4df4-9fbe-4ad597cda211"), "Speed", speed, 0);

        if (speed > 0) {
            player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(mod);
            if (!player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).hasModifier(mod))
                player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(mod);
        }else{ player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(mod); }
    }

    public static void setSpeedFromCap(EntityPlayer player) {
        ISpeedsterCap cap = CapabilitySpeedster.get(player);
            setSpeed(player, cap.getSpeedLevel());
    }

    public static void setSpeedToCap(EntityPlayer player, int level) {
        ISpeedsterCap cap = CapabilitySpeedster.get(player);
        cap.setSpeedLevel(level);
        setSpeed(player, level);
        cap.sync();
    }

    public static void invertProjectilesAroundPlayer(EntityPlayer player, int range) {
        for (Entity e : player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(range))) {
            if (e instanceof IProjectile) {
                NBTTagCompound data = e.getEntityData();
                if (!data.hasKey("edited") || !data.getBoolean("edited")) {
                    data.setBoolean("edited", true);
                    e.setVelocity(-e.motionX, e.motionY, -e.motionZ);
                }
            }
        }
    }


    // Slowmotion

    public static void slowOtherPlayers(EntityPlayer player, int range, float slow) {
        for (EntityPlayer player1 : player.world.getEntitiesWithinAABB(EntityPlayer.class, player.getEntityBoundingBox().grow(range, range, range))) {
            player1.setAIMoveSpeed(slow);
        }
    }

    public static void slowProjectilesAroundPlayer(EntityPlayer player, int range) {
        for (Entity e : player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(range))) {
            if (e instanceof IProjectile) {
                NBTTagCompound data = e.getEntityData();
             //   if (!data.hasKey("edited") || !data.getBoolean("edited")) {
               //     data.setBoolean("edited", true);
                    e.setVelocity(e.motionX * 0.8499f, e.motionY / 2, e.motionZ * 0.8499f);
            }
        }
    }

    public static void sync(EntityPlayer player) {
        player.sendPlayerAbilities();
    }
    
    public static double getPlayerMovementSpeed(EntityPlayer player) {
    	return Math.sqrt(MathHelper.sqr(player.motionX) + MathHelper.sqr(player.motionZ));
    }
    
    public static boolean isPlayerMoving(EntityPlayer player) {
    	return player.motionX != 0 && player.motionZ != 0;
    }
}