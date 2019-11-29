package com.speedstersreborn.tabs;

import com.speedstersreborn.common.blocks.SHRBlockRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SHRTab extends CreativeTabs {

    public SHRTab(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Item.getItemFromBlock(SHRBlockRegistry.SHRBlocks.TREADMILL));
    }
}
