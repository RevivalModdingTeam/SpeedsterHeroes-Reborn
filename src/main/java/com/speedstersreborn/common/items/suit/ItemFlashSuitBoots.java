package com.speedstersreborn.common.items.suit;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.client.models.suits.ModelSuitLegs;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemFlashSuitBoots extends ItemArmor {

    public ItemFlashSuitBoots(String name) {
        super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.LEGS);
        setTranslationKey(name);
        setRegistryName(name);
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        return new ModelSuitLegs(_default);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return SpeedsterHeroesReborn.MODID + ":textures/suits/flash_suit_v1.png";
    }
}
