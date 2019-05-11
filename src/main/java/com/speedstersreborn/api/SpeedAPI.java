package com.speedstersreborn.api;

import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.SHRConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Josia50
 * on 5/04/2019.
 */

public class SpeedAPI {

    public static int MaxSpeedLevel = 20;

    public static void setSpeed(EntityPlayer player, int level) {
        // 2.5F for level 20 (with default config value)
        float speed = level > 0 ? level * SHRConfig.speedstersHeroesReborn.speedIncreaseOverLevel : 0.1f;

        player.capabilities.setPlayerWalkSpeed(speed);
        Sync(player);
    }

    public static void setSpeedFromCap(EntityPlayer player) {
        if (!player.world.isRemote) {
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            setSpeed(player, cap.getSpeedLevel());
        }
    }

    public static void setSpeedToCap(EntityPlayer player, int level) {
        if (!player.world.isRemote) {
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            cap.setSpeedLevel(level);
            setSpeed(player, level);
            cap.sync();
        }
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

    public static void SlowOtherPlayers(EntityPlayer player, int range, float slow) {
        for (EntityPlayer player1 : player.world.getEntitiesWithinAABB(EntityPlayer.class, player.getEntityBoundingBox().grow(range, range, range))) {
            player1.setAIMoveSpeed(slow);
        }
    }

    public static void Sync(EntityPlayer player) {
        player.sendPlayerAbilities();
    }
}