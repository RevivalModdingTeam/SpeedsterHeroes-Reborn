package com.speedstersreborn.common.capabilities;

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

    void setLevel(int level);

    int getLevel();

    void setXP(double xp);

    double getXP();

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
}
