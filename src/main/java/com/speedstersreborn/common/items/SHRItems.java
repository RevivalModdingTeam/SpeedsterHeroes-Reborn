package com.speedstersreborn.common.items;

import com.revivalmodding.revivalcore.core.common.items.ItemEatable;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.suits.FlashSuit;
import com.speedstersreborn.common.suits.ItemFlashSuit;
import com.speedstersreborn.tabs.ModTabs;
import com.speedstersreborn.util.handlers.EnumHandler.RingTypes;
import com.speedstersreborn.util.handlers.EnumHandler.VelocityTypes;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

// TODO make this registry better, same applies for blocks
@Mod.EventBusSubscriber
public class SHRItems {
    public static final List<Item> ITEM_LIST = new ArrayList<>();

    // Rings
    public static ItemRing ring_reverse_flash, ring_zoom, ring_god_speed, ring_kid_flash, ring_flash;
    public static ItemVelocity velocity_nine;
    public static ItemEatable energy_bar;
    public static ItemFlashSuit s1_flash_helmet, s1_flash_chestplate, s1_flash_leggins;
    public static ItemFlashSuit s2_flash_helmet, s2_flash_chestplate, s2_flash_leggins;
    public static ItemFlashSuit s4_flash_helmet, s4_flash_chestplate, s4_flash_leggins;
    public static ItemFlashSuit s5_flash_helmet, s5_flash_chestplate, s5_flash_leggins;

    public static void init() {
        ring_flash = registerItem(new ItemRing("ring_flash", RingTypes.FLASH), true);
        ring_reverse_flash = registerItem(new ItemRing("ring_reverse_flash", RingTypes.REVERSE), true);
        ring_zoom = registerItem(new ItemRing("ring_zoom", RingTypes.ZOOM), true);
        ring_god_speed = registerItem(new ItemRing("ring_god_speed", RingTypes.GOD_SPEED), true);
        ring_kid_flash = registerItem(new ItemRing("ring_kid_flash", RingTypes.KID_FLASH), true);
        velocity_nine = registerItem(new ItemVelocity("velocity_nine", VelocityTypes.VELOCITY_NINE), true);
        energy_bar = registerItem(new ItemEnergyBar("energy_bar", 20, 0, false));
        s1_flash_helmet = registerItem(new ItemFlashSuit("s1_flash_helmet", EntityEquipmentSlot.HEAD).season(FlashSuit.EnumFlashSuitSeason.S1));
        s1_flash_chestplate = registerItem(new ItemFlashSuit("s1_flash_chestplate", EntityEquipmentSlot.CHEST).season(FlashSuit.EnumFlashSuitSeason.S1));
        s1_flash_leggins = registerItem(new ItemFlashSuit("s1_flash_leggings", EntityEquipmentSlot.LEGS).season(FlashSuit.EnumFlashSuitSeason.S1));
        s2_flash_helmet = registerItem(new ItemFlashSuit("s2_flash_helmet", EntityEquipmentSlot.HEAD).season(FlashSuit.EnumFlashSuitSeason.S2));
        s2_flash_chestplate = registerItem(new ItemFlashSuit("s2_flash_chestplate", EntityEquipmentSlot.CHEST).season(FlashSuit.EnumFlashSuitSeason.S2));
        s2_flash_leggins = registerItem(new ItemFlashSuit("s2_flash_leggings", EntityEquipmentSlot.LEGS).season(FlashSuit.EnumFlashSuitSeason.S2));
        s4_flash_helmet = registerItem(new ItemFlashSuit("s4_flash_helmet", EntityEquipmentSlot.HEAD).season(FlashSuit.EnumFlashSuitSeason.S4));
        s4_flash_chestplate = registerItem(new ItemFlashSuit("s4_flash_chestplate", EntityEquipmentSlot.CHEST).season(FlashSuit.EnumFlashSuitSeason.S4));
        s4_flash_leggins = registerItem(new ItemFlashSuit("s4_flash_leggings", EntityEquipmentSlot.LEGS).season(FlashSuit.EnumFlashSuitSeason.S4));
        s5_flash_helmet = registerItem(new ItemFlashSuit("s5_flash_helmet", EntityEquipmentSlot.HEAD).season(FlashSuit.EnumFlashSuitSeason.S5));
        s5_flash_chestplate = registerItem(new ItemFlashSuit("s5_flash_chestplate", EntityEquipmentSlot.CHEST).season(FlashSuit.EnumFlashSuitSeason.S5));
        s5_flash_leggins = registerItem(new ItemFlashSuit("s5_flash_leggings", EntityEquipmentSlot.LEGS).season(FlashSuit.EnumFlashSuitSeason.S5));
    }

    public static void registerRenders() {
        for (Item i : ITEM_LIST) {
            registerRender(i);
        }
    }

    public static <T extends Item> T registerItem(T item) {
        return registerItem(item, true);
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

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(SHRItems.ITEM_LIST.toArray(new Item[0]));
    }

}
