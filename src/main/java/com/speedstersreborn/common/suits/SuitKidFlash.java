package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.common.items.SHRItemRegistry;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

public class SuitKidFlash extends AbstractSuit {

    public SuitKidFlash() {
        super("kid_flash", Color.YELLOW);
    }

    @Override
    public ItemSuit getHelmet() {
        return SHRItemRegistry.SHRItems.S2_KIDFLASH_HEAD;
    }

    @Override
    public ItemSuit getChest() {
        return SHRItemRegistry.SHRItems.S2_KIDFLASH_CHEST;
    }

    @Override
    public ItemSuit getLeggings() {
        return SHRItemRegistry.SHRItems.S1_FLASH_LEGS;
    }

    @Override
    public void handleEffects(EntityPlayer player) {

    }
}
