package com.speedstersreborn.common.blocks;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.revivalmodding.revivalcore.meta.util.PEnumHandler;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockParticleAccelerator extends Block {

    public BlockParticleAccelerator(Material material) {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        PlayerMetaPowerData data = CoreCapabilityImpl.getInstance(playerIn).getMetaPowerData();
        if (playerIn.isSneaking()) {
            data.setPowerActivated(false);
            data.clear();
            CapabilitySpeedster.get(playerIn).clear(); // TODO Find a good place to remove all data written to SPeedCap
        } else { // TODO make real stuff
            data.setMetaPower(PEnumHandler.MetaPower.SPEEDSTER.getID()); // TODO Change to MetaHelper once pushed final
            if (data.getMetaPower() == 0) {
                data.setPowerActivated(true);
            }
        }
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
