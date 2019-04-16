package com.speedstersreborn.proxy;


import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.client.ClientEventHandler;
import com.speedstersreborn.client.renderers.RenderRingDummy;
import com.speedstersreborn.common.entity.EntityRingDummy;
import com.speedstersreborn.common.items.SHRItems;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.util.ResourceLocation;
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
    public void registerModelBakeryVariants() {
        ModelBakery.registerItemVariants(SHRItems.ring, new ResourceLocation(SpeedsterHeroesReborn.MODID, "ring_zoom"), new ResourceLocation(SpeedsterHeroesReborn.MODID, "ring_reverse_flash"));
    }
}
