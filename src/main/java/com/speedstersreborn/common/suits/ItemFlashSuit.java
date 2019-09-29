package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class ItemFlashSuit extends ItemSuit {

    private FlashSuit.EnumFlashSuitSeason season = FlashSuit.EnumFlashSuitSeason.S1;

    public ItemFlashSuit(String name, EntityEquipmentSlot s) {
        super(name, s);
    }

    public ItemFlashSuit season(FlashSuit.EnumFlashSuitSeason season) {
        this.season = season;
        return this;
    }

    @Override
    public ResourceLocation get3DTexture() {
        switch (season) {
            default: return null;
        }
    }
}
