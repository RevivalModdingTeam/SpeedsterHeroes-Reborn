package com.speedstersreborn.api;

import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.SHRConfig;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;

/**
 * Created by Josia50
 * on 5/04/2019.
 */

public class SpeedAPI {

    public static int MaxSpeedLevel = 8;

    public static void setSpeed(EntityPlayer player, int level) {
    	//TODO: For josia, decide how do you want to keep it
        float speed = level > 0 ? level * SHRConfig.speedstersHeroesReborn.speedIncreaseOverLevel : 0.1f;
        /*if(level == 0) {
            speed = 0.1f;
        }

        if(level == 1) {
            speed = 0.3f;
        }

        if(level == 2) {
            speed = 0.5f;
        }

        if(level == 3) {
            speed = 0.7f;
        }

        if(level == 4) {
            speed = 1.0f;
        }

        if(level == 5) {
            speed = 1.2f;
        }

        if(level == 6) {
            speed = 1.4f;
        }

        if(level == 7) {
            speed = 1.6f;
        }

        if(level == 8) {
            speed = 2.5f;
        }*/

        // TODO More speeds but own handler with potion for more speed!!!

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

    // Slowmotion

    public static void SlowProjectiles(EntityPlayer player, int range, double speed) {
        double s = speed; // Default speed is 0.3 //
        int v = 0;

        for (Entity e : player.world.getEntitiesWithinAABB(Entity.class, player.getEntityBoundingBox().grow(range, range, range))) {
            if (e instanceof IProjectile) {
                e.getEntityData().setInteger("edited", 0);
                if (e.getEntityData().getInteger("edited") == 0) {

                    if (e.getHorizontalFacing() == EnumFacing.NORTH) {
                        e.setVelocity(v, v, -s);
                    }

                    if (e.getHorizontalFacing() == EnumFacing.SOUTH) {
                        e.setVelocity(v, v, s);
                    }

                    if (e.getHorizontalFacing() == EnumFacing.EAST) { // TODO FIX EAST
                        e.setVelocity(s, v, v);
                    }                                                // TODO StackTrace = Checks for North & South rotation only could be fixed with angles and being limited angle.

                    if (e.getHorizontalFacing() == EnumFacing.WEST) { // TODO FIX WEST
                        e.setVelocity(s, v, v);
                    }
                    System.out.println(e.getHorizontalFacing());
                    e.getEntityData().setInteger("edited", 1); // TODO use NBT Helper
                }
            }
        }
    }

    public static void SlowOtherPlayers(EntityPlayer player, int range, float slow) {
        for (EntityPlayer player1 : player.world.getEntitiesWithinAABB(EntityPlayer.class, player.getEntityBoundingBox().grow(range, range, range))) {
            player1.setAIMoveSpeed(slow);
        }
    }



    public static void Sync(EntityPlayer player) {
        player.sendPlayerAbilities();
    }
}