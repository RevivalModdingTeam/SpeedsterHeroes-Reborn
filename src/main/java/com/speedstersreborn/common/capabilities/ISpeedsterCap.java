package com.speedstersreborn.common.capabilities;

import com.speedstersreborn.util.handlers.EnumHandler.VelocityTypes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import java.awt.*;

public interface ISpeedsterCap extends INBTSerializable<NBTTagCompound> {

    void update();

    void sync();

    void setSpeedster(boolean speedster);

    boolean isSpeedster();

    void setSpeedLevel(int level);

    int getSpeedLevel();

    void setPhasing(boolean phase);

    boolean isPhasing();

    void setWallRunning(boolean wallRunning);

    boolean isWallRunning();

    void setSecondTrail(boolean secondTrail);

    boolean hasSecondTrail();

    void setSecondaryTrailColor(Color color);

    Color getSecondaryTrailColor();

    void setPrimaryTrailColor(Color color);

    Color getPrimaryTrailColor();

    Color getLastTrailColor();

    void setLastTrailColor(Color color);

    void setRainbowTrail(boolean rainbowTrail);

    boolean hasRainbowTrail();

    void setMaxSpeedLevel(int level);

    int getMaxspeedLevel();

    void setVelocity(VelocityTypes types);

    void setVelocity(boolean on);

    void setVelocityTime(int time);

    int getVelocityTime();

    boolean hasTakenVelocityBefore();

    int velocityUses();

    int getAddedSpeed();

    boolean hasVelocity();

    void setHungerTimer(int hungerTimer);

    int getHungerTimer();

    void clear();

    void clearV9();
}
