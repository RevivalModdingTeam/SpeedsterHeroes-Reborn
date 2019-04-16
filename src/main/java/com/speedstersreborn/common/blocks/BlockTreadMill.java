package com.speedstersreborn.common.blocks;

import com.speedstersreborn.common.entity.EntityTreadmill;
import com.speedstersreborn.common.tileentity.TileTreadMill;
import com.speedstersreborn.util.helper.IHasItem;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTreadMill extends Block implements ITileEntityProvider, IHasItem {
    public BlockTreadMill(Material material) {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileTreadMill treadMill = (TileTreadMill) worldIn.getTileEntity(pos);
        if (treadMill != null) {
            if (!treadMill.getRidden()) {
                EntityTreadmill entity = new EntityTreadmill(worldIn);
                entity.setPos(pos);
                entity.setPosition(pos.getX(), pos.getY(), pos.getZ());
                treadMill.setRidden();
                worldIn.spawnEntity(entity);
                playerIn.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            } else {
                treadMill.setRidden();
            }
        }
        return true;
    }


    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(0.2, 0, 0, 0.7, 0.4, 1);
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTreadMill();
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
