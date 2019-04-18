package com.speedstersreborn.common.items;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.items.suit.ItemFlashSuitBoots;
import com.speedstersreborn.common.items.suit.ItemFlashSuitHelm;
import com.speedstersreborn.tabs.ModTabs;
import com.speedstersreborn.util.handlers.EnumHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

public class SHRItems {
    public static final List<Item> ITEM_LIST = new ArrayList<>();

    public static Item ring, flashboots, flashhelmet;

    public static void init() {
        ring = registerItem(new ItemRing("ring"), true);
        flashhelmet = registerItem(new ItemFlashSuitHelm("flash_suit_helmet"), true);
        flashboots = registerItem(new ItemFlashSuitBoots("flash_suit_boots"), true);
    }


    public static void registerRenders() {
        registerRender(flashhelmet);
        registerRender(flashboots);
       for (int i = 0; i < EnumHandler.RingTypes.values().length; i++) {
            registerRenderMeta(ring, i, "ring_" + EnumHandler.RingTypes.values()[i].getName());
        }
    }

    public static Item registerItem(Item item, boolean tab) {
        if (tab)
            item.setCreativeTab(ModTabs.shrTab);
        ITEM_LIST.add(item);
        return item;
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static void registerRenderMeta(Item item, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(SpeedsterHeroesReborn.MODID, name), "inventory"));
    }
}
