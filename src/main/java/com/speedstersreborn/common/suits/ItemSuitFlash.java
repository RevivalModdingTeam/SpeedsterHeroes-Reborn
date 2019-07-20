package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.client.models.suits.S1FlashHelmet;
import com.speedstersreborn.common.items.SHRItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemSuitFlash extends ItemSuit {
	
	private final S1FlashHelmet helmet = new S1FlashHelmet();
	
	public ItemSuitFlash(String name, ArmorMaterial mat, int i, EntityEquipmentSlot slot) {
		super(name, mat, i, slot);
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
		if(!itemStack.isEmpty()) {
			if(itemStack.getItem() == SHRItems.flash_helmet) {
				helmet.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
				return helmet;
			}
		}
		return null;
	}

	@Nullable
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
		return SpeedsterHeroesReborn.MODID + ":textures/models/armor/flashs1.png";
	}
}
