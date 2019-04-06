package com.speedstersreborn.common.entity;

import com.speedstersreborn.common.tileentity.TileTreadMill;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityTreadmill extends Entity {

    public static BlockPos position = BlockPos.ORIGIN;

    public EntityTreadmill(World worldIn) {
        super(worldIn);
        setSize(1f, 0.3f);
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {

    }

    public void setPos(BlockPos pos) {
        position = pos;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!world.isRemote) {
            if (position == null || BlockPos.ORIGIN.equals(position))
                remove();
            if (this.getPassengers() == null || this.getPassengers().size() < 1)
                remove();
        }
    }

    public void remove() {
        if (position != null && !BlockPos.ORIGIN.equals(position)) {
            TileTreadMill treadMill = (TileTreadMill) world.getTileEntity(position);
            treadMill.ridden = false;
        }
        this.setDead();
    }
}
