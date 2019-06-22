package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class ItemSuitFlash extends ItemSuit {
	
	public ItemSuitFlash(String name, ArmorMaterial mat, int i, EntityEquipmentSlot slot) {
		super(name, mat, i, slot);
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return super.getArmorTexture(stack, entity, slot, type);
	}
}
