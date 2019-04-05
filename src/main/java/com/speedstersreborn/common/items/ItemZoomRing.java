package com.speedstersreborn.common.items;

import com.revivalcore.revivalcore.RevivalCore;
import com.speedstersreborn.util.handlers.IHasModel;
import net.minecraft.item.Item;

public class ItemZoomRing extends Item implements IHasModel {

    public ItemZoomRing(String name) {
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(RevivalCore.coretab);
        SHRItems.ITEM_LIST.add(this);
    }

    @Override
    public void registerModels() {
        RevivalCore.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
