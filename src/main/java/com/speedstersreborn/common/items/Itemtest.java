package com.speedstersreborn.common.items;

import com.revivalmodding.revivalcore.core.common.items.ItemLocked;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.entity.EntityTrailThrow;
import com.speedstersreborn.util.handlers.client.TrailRenderHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Itemtest extends ItemLocked {

    public Itemtest(String name) {
        super();
        setRegistryName(name);
        setTranslationKey(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        if (!worldIn.isRemote) {
            EntityTrailThrow r = new EntityTrailThrow(worldIn, playerIn, CapabilitySpeedster.get(playerIn).getPrimaryTrailColor(), TrailRenderHandler.TrailType.NORMAL);
            r.shoot(playerIn, playerIn.rotationPitch, playerIn.getRotationYawHead(), 0, 1, 0);
            worldIn.spawnEntity(r);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
