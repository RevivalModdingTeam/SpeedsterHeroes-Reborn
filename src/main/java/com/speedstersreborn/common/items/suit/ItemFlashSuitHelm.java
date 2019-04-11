package com.speedstersreborn.common.items.suit;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.client.models.suits.ModelHead;
import com.speedstersreborn.common.items.SHRItems;
import com.speedstersreborn.util.handlers.IHasModel;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemFlashSuitHelm extends ItemArmor implements IHasModel {

    public ItemFlashSuitHelm(String name) {
        super(ArmorMaterial.LEATHER, 0, EntityEquipmentSlot.HEAD);
        setTranslationKey(name);
        setRegistryName(name);

        SHRItems.ITEM_LIST.add(this);
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        return new ModelHead(_default);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return SpeedsterHeroesReborn.MODID + ":textures/suits/flash_suit_v1.png";
    }

    @Override
    public void registerModels() {
        SpeedsterHeroesReborn.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
