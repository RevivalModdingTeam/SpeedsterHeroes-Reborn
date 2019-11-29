package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.SpeedsterHeroesReborn;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class ItemSuitKidFlash extends ItemSuit {

    private static final ResourceLocation TEXTURE = new ResourceLocation(SpeedsterHeroesReborn.MODID + ":textures/suits/s2kidflash.png");

    public ItemSuitKidFlash(String name, EntityEquipmentSlot slot) {
        super(name, slot);
    }

    @Override
    public ResourceLocation get3DTexture() {
        return TEXTURE;
    }
}
