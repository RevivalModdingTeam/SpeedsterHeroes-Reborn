package com.speedstersreborn.proxy;


import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.client.ClientEventHandler;
import com.speedstersreborn.client.renderers.RenderRingDummy;
import com.speedstersreborn.common.entity.EntityRingDummy;
import com.speedstersreborn.common.items.SHRItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {

    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRingDummy.class, RenderRingDummy::new);
    }

    @Override
    public void init() {
        ClientEventHandler.init();
    }

    @Override
    public void registerItemRenderer(Item item, int meta, String filename, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(SpeedsterHeroesReborn.MODID, filename), id));
    }

    @Override
    public void registerModelBakeryVariants() {
        ModelBakery.registerItemVariants(SHRItems.RING, new ResourceLocation(SpeedsterHeroesReborn.MODID, "ring_zoom"), new ResourceLocation(SpeedsterHeroesReborn.MODID, "ring_reverse_flash"));
    }
}
