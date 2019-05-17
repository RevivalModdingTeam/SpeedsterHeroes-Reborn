package com.speedstersreborn.common.items;


import com.revivalmodding.revivalcore.util.helper.StringHelper;
import com.speedstersreborn.common.entity.EntityRingDummy;
import com.speedstersreborn.util.handlers.EnumHandler.RingTypes;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRing extends Item {

	private final RingTypes ringType;
	
    public ItemRing(String name, RingTypes typeOfRing) {
        setTranslationKey(name);
        setRegistryName(name);
        this.ringType = typeOfRing;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if(stack.getTagCompound().getBoolean("has_suit")) {
            stack.getTagCompound().setBoolean("has_suit", false);

            EntityRingDummy rd = new EntityRingDummy(worldIn, ringType.getBsuit());
            rd.setLocationAndAngles(playerIn.posX + 2, playerIn.posY, playerIn.posZ + 2, playerIn.rotationYaw, playerIn.rotationPitch - 0.5f);
            worldIn.spawnEntity(rd);
        }else{

        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextFormatting.BOLD + StringHelper.translateToLocal( getTranslationKey() + ".name");
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return this.getTranslationKey();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    	tooltip.add(TextFormatting.GRAY + "" + TextFormatting.BOLD + "Click to summon " + ringType.getName() + "'s suit!");
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("has_suit", true);
        }
    }
}
