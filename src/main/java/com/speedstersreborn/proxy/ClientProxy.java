package com.speedstersreborn.proxy;


import com.speedstersreborn.client.renderers.RenderEntityTrail;
import com.speedstersreborn.client.renderers.RenderEntityTrailSecond;
import com.speedstersreborn.client.renderers.RenderRingDummy;
import com.speedstersreborn.common.entity.EntityRingDummy;
import com.speedstersreborn.util.handlers.client.SHKeybinds;
import com.speedstersreborn.util.handlers.client.TrailRenderHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {

    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityRingDummy.class, RenderRingDummy::new);
        RenderingRegistry.registerEntityRenderingHandler(TrailRenderHandler.EntityTrail.class, RenderEntityTrail::new);
        RenderingRegistry.registerEntityRenderingHandler(TrailRenderHandler.EntityTrailSecond.class, RenderEntityTrailSecond::new);
    }

    @Override
    public void init() {
    	// SHKeybinds.init();
    }

    @Override
    public void registerModelBakeryVariants() {
    }
}
