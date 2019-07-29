package com.speedstersreborn.proxy;


import com.speedstersreborn.client.models.suits.S1FlashHelmet;
import com.speedstersreborn.client.renderers.RenderEntityTrail;
import com.speedstersreborn.client.renderers.RenderEntityTrailSecond;
import com.speedstersreborn.client.renderers.RenderRingDummy;
import com.speedstersreborn.common.entity.EntityRingDummy;
import com.speedstersreborn.util.handlers.client.TrailRenderHandler;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy {

    private final S1FlashHelmet helmet_s1Flash = new S1FlashHelmet();

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
    public void postInit() {

    }

    @Override
    public void registerModelBakeryVariants() {
    }
}
