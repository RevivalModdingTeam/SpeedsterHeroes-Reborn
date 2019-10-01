package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.common.items.SHRItemRegistry;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

public class SuitGodSpeed extends AbstractSuit {

    public SuitGodSpeed() {
        super("godspeed", Color.LIGHT_GRAY);
    }

    @Override
    public ItemSuit getHelmet() {
        return SHRItemRegistry.SHRItems.S5_GODSPEED_HEAD;
    }

    @Override
    public ItemSuit getChest() {
        return SHRItemRegistry.SHRItems.S5_GODSPEED_CHEST;
    }

    @Override
    public ItemSuit getLeggings() {
        return SHRItemRegistry.SHRItems.S5_GODSPEED_LEGS;
    }

    @Override
    public void handleEffects(EntityPlayer player) {

    }
}
