package com.speedstersreborn.common.blocks;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.items.SHRItemRegistry;
import com.speedstersreborn.tabs.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SHRBlockRegistry {

    @GameRegistry.ObjectHolder(SpeedsterHeroesReborn.MODID)
    public static final class SHRBlocks {
        public static final BlockTreadMill TREADMILL = null;
        public static final BlockParticleAccelerator SMALL_PARTICLE_ACCELERATOR = null;
    }

    @Mod.EventBusSubscriber
    public static class Registry {

        @SubscribeEvent
        public static void onBlockRegistry(RegistryEvent.Register<Block> e) {
            e.getRegistry().registerAll(
                    register(new BlockTreadMill(Material.ROCK), "treadmill"),
                    register(new BlockParticleAccelerator(Material.IRON), "small_particle_accelerator")
            );
        }

        private static <BLOCK extends Block> BLOCK register(BLOCK block, String name) {
            block.setRegistryName(name);
            block.setTranslationKey(name);
            block.setCreativeTab(ModTabs.shrTab);
            SHRItemRegistry.Registry.addItemBlock(block);
            return block;
        }
    }
}
