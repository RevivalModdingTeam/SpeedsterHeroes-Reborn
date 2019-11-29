package com.speedstersreborn.common.items;

import com.revivalmodding.revivalcore.core.common.items.ItemEatable;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuitMultiSeason;
import com.revivalmodding.revivalcore.util.helper.ModHelper;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.tabs.ModTabs;
import com.speedstersreborn.util.handlers.EnumHandler.RingTypes;
import com.speedstersreborn.util.handlers.EnumHandler.VelocityTypes;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;

public class SHRItemRegistry {

    @GameRegistry.ObjectHolder(SpeedsterHeroesReborn.MODID)
    public static final class SHRItems {
        public static final ItemRing RING_REVERSE_FLASH = null;
        public static final ItemRing RING_ZOOM = null;
        public static final ItemRing RING_GOD_SPEED = null;
        public static final ItemRing RING_FLASH = null;
        public static final ItemRing RING_KID_FLASH = null;
        public static final ItemVelocity VELOCITY_NINE = null;
        public static final ItemEatable ENERGY_BAR = null;
        public static final ItemSuitMultiSeason S1_FLASH_HEAD = null;
        public static final ItemSuitMultiSeason S1_FLASH_CHEST = null;
        public static final ItemSuitMultiSeason S1_FLASH_LEGS = null;
        public static final ItemSuitMultiSeason S2_FLASH_HEAD = null;
        public static final ItemSuitMultiSeason S2_FLASH_CHEST = null;
        public static final ItemSuitMultiSeason S2_FLASH_LEGS = null;
        public static final ItemSuitMultiSeason S4_FLASH_HEAD = null;
        public static final ItemSuitMultiSeason S4_FLASH_CHEST = null;
        public static final ItemSuitMultiSeason S4_FLASH_LEGS = null;
        public static final ItemSuitMultiSeason S5_FLASH_HEAD = null;
        public static final ItemSuitMultiSeason S5_FLASH_CHEST = null;
        public static final ItemSuitMultiSeason S5_FLASH_LEGS = null;
        public static final ItemSuit S2_KIDFLASH_HEAD = null;
        public static final ItemSuit S2_KIDFLASH_CHEST = null;
        public static final ItemSuit S2_KIDFLASH_LEGS = null;
        public static final ItemSuit S1_REVERSEFLASH_HEAD = null;
        public static final ItemSuit S1_REVERSEFLASH_CHEST = null;
        public static final ItemSuit S1_REVERSEFLASH_LEGS = null;
        public static final ItemSuit S2_ZOOM_HEAD = null;
        public static final ItemSuit S2_ZOOM_CHEST = null;
        public static final ItemSuit S2_ZOOM_LEGS = null;
        public static final ItemSuit S2_BLACK_FLASH_HEAD = null;
        public static final ItemSuit S2_BLACK_FLASH_CHEST = null;
        public static final ItemSuit S2_BLACK_FLASH_LEGS = null;
        public static final ItemSuit S5_GODSPEED_HEAD = null;
        public static final ItemSuit S5_GODSPEED_CHEST = null;
        public static final ItemSuit S5_GODSPEED_LEGS = null;
    }

    @Mod.EventBusSubscriber
    public static class Registry {

        private static List<ItemBlock> blockItems = new ArrayList<>();

        @SubscribeEvent
        public static void onItemRegistry(RegistryEvent.Register<Item> e) {
            e.getRegistry().registerAll(
                    addItem(new ItemRing("ring_reverse_flash", RingTypes.REVERSE)),
                    addItem(new ItemRing("ring_zoom", RingTypes.ZOOM)),
                    addItem(new ItemRing("ring_god_speed", RingTypes.GOD_SPEED)),
                    addItem(new ItemRing("ring_flash", RingTypes.FLASH)),
                    addItem(new ItemRing("ring_kid_flash", RingTypes.KID_FLASH)),
                    addItem(new ItemVelocity("velocity_nine", VelocityTypes.VELOCITY_NINE)),
                    new ItemEatable("energy_bar", 20, 0, false)
            );

            ItemSuitMultiSeason.Season s1 = new ItemSuitMultiSeason.Season("s1");
            ItemSuitMultiSeason.Season s2 = new ItemSuitMultiSeason.Season("s2");
            ItemSuitMultiSeason.Season s4 = new ItemSuitMultiSeason.Season("s4");
            ItemSuitMultiSeason.Season s5 = new ItemSuitMultiSeason.Season("s5");

            ModHelper.registerMultiSeasonSuit(e, ModTabs.shrTab, "flash", s1, s2, s4, s5);
            ModHelper.registerMultiSeasonSuit(e, ModTabs.shrTab, "kidflash", s2);
            ModHelper.registerMultiSeasonSuit(e, ModTabs.shrTab, "reverseflash", s1);
            ModHelper.registerMultiSeasonSuit(e, ModTabs.shrTab, "zoom", s2);
            ModHelper.registerMultiSeasonSuit(e, ModTabs.shrTab, "blackflash", s2);
            ModHelper.registerMultiSeasonSuit(e, ModTabs.shrTab, "godspeed", s5);

            blockItems.forEach(blockItem -> e.getRegistry().register(blockItem));
            blockItems = null;
        }

        public static void addItemBlock(Block block) {
            ItemBlock itemBlock = new ItemBlock(block);
            itemBlock.setRegistryName(block.getRegistryName());
            blockItems.add(itemBlock);
        }

        private static <ITEM extends Item> ITEM addItem(ITEM item) {
            return addItem(item, ModTabs.shrTab);
        }

        private static <ITEM extends Item> ITEM addItem(ITEM item, CreativeTabs tab) {
            item.setCreativeTab(tab);
            return item;
        }
    }
}
