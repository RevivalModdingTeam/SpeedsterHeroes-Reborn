package com.speedstersreborn.common.items;

import com.speedstersreborn.common.entity.EntityTrailThrow;
import com.speedstersreborn.util.handlers.client.TrailRenderHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class Itemtest extends Item {

    public Itemtest(String name) {
        setRegistryName(name);
        setTranslationKey(name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        if(!worldIn.isRemote) {
            EntityTrailThrow r = new EntityTrailThrow(worldIn, playerIn, TrailRenderHandler.TrailType.NORMAL);
            r.shoot(playerIn, playerIn.rotationPitch, playerIn.getRotationYawHead(), 0,1,0);
            worldIn.spawnEntity(r);
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
