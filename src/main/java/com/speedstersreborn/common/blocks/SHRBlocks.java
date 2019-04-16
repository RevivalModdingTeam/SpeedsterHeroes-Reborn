package com.speedstersreborn.common.blocks;

import com.speedstersreborn.common.items.SHRItems;
import com.speedstersreborn.tabs.ModTabs;
import com.speedstersreborn.util.helper.IHasItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;
import java.util.List;

public class SHRBlocks {
    public static final List<Block> BLOCK_LIST = new ArrayList<Block>();

    public static Block TREADMILL = RegisterBlock(new BlockTreadMill(Material.ROCK), "treadmill", true);

    public static Block RegisterBlock(Block block, String name, boolean tab) {
        block.setRegistryName(name);
        block.setTranslationKey(name);
        SHRBlocks.BLOCK_LIST.add(block);

        if (block instanceof IHasItem) {
            ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(name);

            if (tab) {
                block.setCreativeTab(ModTabs.shrTab);
            }
            SHRItems.registerRender(itemBlock);
            SHRItems.ITEM_LIST.add(itemBlock);

        }
        return block;
    }
}
