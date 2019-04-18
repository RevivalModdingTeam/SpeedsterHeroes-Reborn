package com.speedstersreborn.api;

import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Josia50
 * on 5/04/2019.
 */

public class SpeedAPI {

    public static int MaxSpeedLevel = 8;

    public static void setSpeed(EntityPlayer player, int level) {
        float speed = 0.1f;

        if(level == 0) {
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
        }

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



    public static void Sync(EntityPlayer player) {
        player.sendPlayerAbilities();
    }
}