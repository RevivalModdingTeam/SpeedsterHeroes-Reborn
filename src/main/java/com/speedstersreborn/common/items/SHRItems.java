package com.speedstersreborn.common.items;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.tabs.ModTabs;
import com.speedstersreborn.util.handlers.EnumHandler.RingTypes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.ArrayList;
import java.util.List;

public class SHRItems {
    public static final List<Item> ITEM_LIST = new ArrayList<>();

    // Rings
    public static ItemRing ring_reverse_flash, ring_zoom, ring_god_speed, ring_kid_flash;

    public static void init() {
        ring_reverse_flash = registerItem(new ItemRing("ring_reverse_flash", RingTypes.REVERSE), true);
        ring_zoom = registerItem(new ItemRing("ring_zoom", RingTypes.ZOOM), true);
        ring_god_speed = registerItem(new ItemRing("ring_god_speed", RingTypes.GOD_SPEED), true);
        ring_kid_flash = registerItem(new ItemRing("ring_kid_flash", RingTypes.KID_FLASH), true);
    }

    public static void registerRenders() {
        for (Item i : ITEM_LIST) {
            registerRender(i);
        }
    }

    public static <T extends Item> T registerItem(T item) {
        SHRItems.ITEM_LIST.add(item);
        return item;
    }

    public static <T extends Item> T registerItem(T item, boolean tab) {
        if (tab)
            item.setCreativeTab(ModTabs.shrTab);
        return registerItem(item);
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static void registerRenderMeta(Item item, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(SpeedsterHeroesReborn.MODID, name), "inventory"));
    }
}
