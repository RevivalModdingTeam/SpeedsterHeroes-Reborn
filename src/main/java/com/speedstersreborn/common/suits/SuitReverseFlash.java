package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.common.items.SHRItemRegistry;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

public class SuitReverseFlash extends AbstractSuit {

    public SuitReverseFlash() {
        super("reverseflash", Color.BLACK);
    }

    @Override
    public ItemSuit getHelmet() {
        return SHRItemRegistry.SHRItems.S1_REVERSEFLASH_HEAD;
    }

    @Override
    public ItemSuit getChest() {
        return SHRItemRegistry.SHRItems.S1_REVERSEFLASH_CHEST;
    }

    @Override
    public ItemSuit getLeggings() {
        return SHRItemRegistry.SHRItems.S1_REVERSEFLASH_LEGS;
    }

    @Override
    public void handleEffects(EntityPlayer player) {

    }
}
