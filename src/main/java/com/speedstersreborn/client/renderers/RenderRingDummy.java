package com.speedstersreborn.client.renderers;

import com.speedstersreborn.common.entity.EntityRingDummy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.util.ResourceLocation;

public class RenderRingDummy extends RenderLivingBase<EntityRingDummy> {

    public RenderRingDummy(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelBiped(0), 0.5F);
        this.addLayer(new LayerBipedArmor(this));
    }

    @Override
    protected void renderLivingLabel(EntityRingDummy entityIn, String str, double x, double y, double z, int maxDistance) {
    }

    @Override
    public void doRender(EntityRingDummy entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRingDummy entity) {
        return null;
    }

}
