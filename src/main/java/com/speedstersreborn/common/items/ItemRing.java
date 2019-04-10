package com.speedstersreborn.common.items;


import com.revivalcore.core.RevivalCore;
import com.revivalcore.util.helper.StringHelper;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.util.handlers.EnumHandler.RingTypes;
import com.speedstersreborn.util.handlers.IHasModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

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
        for (int i = 0; i < RingTypes.values().length; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return TextFormatting.BOLD + StringHelper.translateToLocal( getTranslationKey() + ".name");
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
      /*  for (int i = 0; i < RingTypes.values().length; i++) {
            if (stack.getItemDamage() == i) {
                return this.getTranslationKey() + "." + RingTypes.values()[i].getName();
            } else {
                continue;
            }
        }*/
        return this.getTranslationKey();
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        for (int i = 0; i < RingTypes.values().length; i++) {
            if (stack.getItemDamage() == i) {
                tooltip.add(TextFormatting.GRAY + "" + TextFormatting.BOLD + "Click to summon " + RingTypes.values()[i].getName() + "'s suit!");
            } else {
                continue;
            }
        }
    }

    @Override
    public void registerModels() {
        for (int i = 0; i < RingTypes.values().length; i++) {
            SpeedsterHeroesReborn.proxy.registerItemRendererMeta(this, i, "ring_" + RingTypes.values()[i].getName(), "inventory");
        }
    }
}