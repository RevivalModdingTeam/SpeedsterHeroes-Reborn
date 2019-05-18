package com.speedstersreborn.common.blocks;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.helper.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockParticleAccelerator extends Block implements IHaveItem {

    public BlockParticleAccelerator(Material material) {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IMetaCap cap = CapabilityMeta.get(playerIn);
        ISpeedsterCap capa = CapabilitySpeedster.get(playerIn);
        if (playerIn.isSneaking()) {
            capa.setSpeedster(false);
            cap.clear();
        } else { // TODO make real stuff
            cap.setMetaPower(0);
            if(cap.getMetaPower() == 0) {
                capa.setSpeedster(true);
            }
        }
        return true;
    }

    @Override
    public boolean hasItem() {
        return true;
    }
}
