package com.speedstersreborn.client.renderers;

import com.speedstersreborn.client.models.entity.ModelRingEntity;
import com.speedstersreborn.common.entity.EntityRingDummy;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderRingDummy extends RenderLivingBase<EntityRingDummy> {


    public RenderRingDummy(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelRingEntity(), 0);
    }

    @Override
    protected void renderLivingLabel(EntityRingDummy entityIn, String str, double x, double y, double z, int maxDistance) {
    }

    @Override
    public void doRender(EntityRingDummy entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityRingDummy entity) {
        return null;
    }
}
