package com.speedstersreborn.common.items;

import java.util.ArrayList;
import java.util.List;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.tabs.ModTabs;
import com.speedstersreborn.util.handlers.EnumHandler.RingTypes;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class SHRItems {
    public static final List<Item> ITEM_LIST = new ArrayList<>();

    public static Item ring_reverse_flash;
    public static Item ring_zoom;
    public static Item ring_god_speed;
    public static Item ring_kid_flash;

    public static void init() {
        ring_reverse_flash = registerItem(new ItemRing("ring_reverse_flash", RingTypes.REVERSE), true);
        ring_zoom = registerItem(new ItemRing("ring_zoom", RingTypes.ZOOM), true);
        ring_god_speed = registerItem(new ItemRing("ring_god_speed", RingTypes.GOD_SPEED), true);
        ring_kid_flash = registerItem(new ItemRing("ring_kid_flash", RingTypes.KID_FLASH), true);
    }


    // by Toma, automated item render registry
    public static void registerRenders() {
    	final IForgeRegistry<Item> ITEMS = ForgeRegistries.ITEMS;
    	for(ResourceLocation rl : ITEMS.getKeys()) {
    		if(rl.getNamespace().equals(SpeedsterHeroesReborn.MODID)) {
    			registerRender(ITEMS.getValue(rl));
    		}
    	}
    }

    public static Item registerItem(Item item, boolean tab) {
        if (tab)
            item.setCreativeTab(ModTabs.shrTab);
        SHRItems.ITEM_LIST.add(item);
        return item;
    }

    public static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    public static void registerRenderMeta(Item item, int meta, String name) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(SpeedsterHeroesReborn.MODID, name), "inventory"));
    }
}
