package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.common.items.SHRItemRegistry;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

public class SuitZoom extends AbstractSuit {

    public SuitZoom() {
        super("zoom", Color.DARK_GRAY);
    }

    @Override
    public ItemSuit getHelmet() {
        return SHRItemRegistry.SHRItems.S2_ZOOM_HEAD;
    }

    @Override
    public ItemSuit getChest() {
        return SHRItemRegistry.SHRItems.S2_ZOOM_CHEST;
    }

    @Override
    public ItemSuit getLeggings() {
        return SHRItemRegistry.SHRItems.S2_ZOOM_LEGS;
    }

    @Override
    public void handleEffects(EntityPlayer player) {

    }
}
