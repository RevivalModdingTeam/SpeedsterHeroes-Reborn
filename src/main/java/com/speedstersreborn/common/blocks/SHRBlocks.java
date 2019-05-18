package com.speedstersreborn.common.blocks;

import com.speedstersreborn.common.items.SHRItems;
import com.speedstersreborn.tabs.ModTabs;
import com.speedstersreborn.util.helper.IHaveItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class SHRBlocks {

    public static final List<Block> BLOCK_LIST = new ArrayList<>();

    public static Block treadmill = RegisterBlock(new BlockTreadMill(Material.ROCK), "treadmill", true);
    public static Block particleaccelerator = RegisterBlock(new BlockParticleAccelerator(Material.IRON), "small_particle_accelerator", true);

    public static Block RegisterBlock(Block block, String name, boolean tab) {
        block.setRegistryName(name);
        block.setTranslationKey(name);
        SHRBlocks.BLOCK_LIST.add(block);

        if (block instanceof IHaveItem) {
            if (((IHaveItem) block).hasItem()) {
                ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).setRegistryName(name);

                if (tab) {
                    block.setCreativeTab(ModTabs.shrTab);
                }
                SHRItems.ITEM_LIST.add(itemBlock);
            }
        }
        return block;
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(SHRBlocks.BLOCK_LIST.toArray(new Block[0]));
    }
}
