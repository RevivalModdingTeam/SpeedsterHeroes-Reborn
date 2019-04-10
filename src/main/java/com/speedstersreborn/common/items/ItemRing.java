package com.speedstersreborn.common.items;


import com.revivalcore.core.RevivalCore;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.util.handlers.EnumHandler;
import com.speedstersreborn.util.handlers.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemRing extends Item implements IHasModel {

    public ItemRing(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(RevivalCore.coretab);
        setHasSubtypes(true);

        SHRItems.ITEM_LIST.add(this);
    }


    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < EnumHandler.RingTypes.values().length; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        for (int i = 0; i < EnumHandler.RingTypes.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getTranslationKey() + "." + EnumHandler.RingTypes.values()[i].getName();
            } else {
                continue;
            }
        }
        return this.getTranslationKey();
    }

    @Override
    public void registerModels() {
        for (int i = 0; i < EnumHandler.RingTypes.values().length; i++) {
            SpeedsterHeroesReborn.proxy.registerItemRenderer(this, i, "ring_" + EnumHandler.RingTypes.values()[i].getName(), "inventory");
        }
    }
}
