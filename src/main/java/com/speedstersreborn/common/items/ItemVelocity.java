package com.speedstersreborn.common.items;

import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.handlers.EnumHandler.VelocityTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.awt.*;

public class ItemVelocity extends Item {

    VelocityTypes velocityTypes;

    public ItemVelocity(String name, VelocityTypes types) {
        setTranslationKey(name);
        setRegistryName(name);
        this.velocityTypes = types;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if(stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("has_drug", true);
        }

        if(stack.getTagCompound().getBoolean("has_drug")) {
            stack.getTagCompound().setBoolean("has_drug", false);
            ISpeedsterCap cap = CapabilitySpeedster.get(playerIn);
            if(!MetaHelper.hasPower(playerIn, MetaPowerStrings.SPEEDSTER)) {
                cap.setVelocity(velocityTypes, true);
                cap.setPrimaryTrailColor(Color.BLUE);
                cap.setSpeedster(true);
                cap.sync();
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
