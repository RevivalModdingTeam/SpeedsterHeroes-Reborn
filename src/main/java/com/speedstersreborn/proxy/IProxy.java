package com.speedstersreborn.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.inventory.EntityEquipmentSlot;

public interface IProxy {

    void preInit();

    void init();

    void postInit();

    void registerModelBakeryVariants();
}
