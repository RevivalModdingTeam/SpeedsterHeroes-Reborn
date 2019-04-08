package com.speedstersreborn.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileTreadMill extends TileEntity {
    public boolean ridden = false;

    public void setRidden() {
        ridden = !ridden;
    }

    public boolean getRidden()  {
        return ridden;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ridden = compound.getBoolean("ridden");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
         super.writeToNBT(compound);
         compound.setBoolean("ridden", ridden);
         return compound;
    }
}
