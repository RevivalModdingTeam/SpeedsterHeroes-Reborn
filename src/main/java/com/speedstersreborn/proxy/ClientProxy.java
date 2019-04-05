package com.speedstersreborn.proxy;


import com.speedstersreborn.client.renderers.RenderRingDummy;
import com.speedstersreborn.common.entity.EntityRingDummy;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {

    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRingDummy.class, RenderRingDummy::new);
    }

    @Override
    public void init() {

    }
}
