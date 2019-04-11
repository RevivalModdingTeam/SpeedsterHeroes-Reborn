package com.speedstersreborn.common.items;

import com.speedstersreborn.common.items.suit.ItemFlashSuitBoots;
import com.speedstersreborn.common.items.suit.ItemFlashSuitHelm;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class SHRItems {
    public static final List<Item> ITEM_LIST = new ArrayList<Item>();

    public static final Item RING = new ItemRing("ring");
    public static final Item FLASH_SUIT_BOOTS = new ItemFlashSuitBoots("flash_suit_boots");
    public static final Item FLASH_SUIT_HELMET = new ItemFlashSuitHelm("flash_suit_helmet");
}
