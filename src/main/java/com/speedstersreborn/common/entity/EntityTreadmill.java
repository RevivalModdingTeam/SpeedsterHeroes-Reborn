package com.speedstersreborn.common.entity;

import com.speedstersreborn.common.blocks.SHRBlocks;
import com.speedstersreborn.common.tileentity.TileTreadMill;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTreadmill extends Entity {

    public static BlockPos position = BlockPos.ORIGIN;

    public EntityTreadmill(World worldIn) {
        super(worldIn);
        setSize(0f,0f);
    }

    @Override
    protected void entityInit() {

    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return new AxisAlignedBB(0,0,0,0,0,0);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer entityIn) {
        super.onCollideWithPlayer(entityIn);
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
            if (world.getBlockState(position).getBlock() != SHRBlocks.TREADMILL) {
                remove();
            } else {
                TileTreadMill tile = (TileTreadMill) world.getTileEntity(position);
                if (tile.getRidden() == false) {
                    remove();
                }
            }
        }
    }

    public void remove() {
        if (world.getBlockState(position).getBlock() != SHRBlocks.TREADMILL) {
            this.setDead();
            return;
        }
        if (position != null && !BlockPos.ORIGIN.equals(position)) {
            TileTreadMill treadMill = (TileTreadMill) world.getTileEntity(position);
            treadMill.ridden = false;
        }
        this.setDead();
    }
}
