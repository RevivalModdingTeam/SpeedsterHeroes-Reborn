package com.speedstersreborn.client.models.suits;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelPlayerAlt - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelHead extends ModelBiped {
    public ModelRenderer field_178720_f;
    public ModelRenderer field_78116_c;
    public ModelBiped modelBiped;

    public ModelHead(ModelBiped biped) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.modelBiped = biped;

        this.field_78116_c = new ModelRenderer(this, 0, 0);
        this.field_78116_c.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_78116_c.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.field_178720_f = new ModelRenderer(this, 32, 0);
        this.field_178720_f.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.field_178720_f.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.pushMatrix();
        if (entity != null && entity.isSneaking()) {
            GlStateManager.translate(0, 0.25F, 0);
        }
        setRotationAngles(f,f1,f2,f3,f4,f5, entity);
        this.field_78116_c.render(f5);
        this.field_178720_f.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,  headPitch, scaleFactor, entityIn);
        //this.field_178720_f.rotateAngleZ = bipedHead.rotateAngleZ;
        //this.field_78116_c.rotateAngleZ = bipedHead.rotateAngleZ;
        this.field_178720_f.rotateAngleX = bipedHead.rotateAngleX;
        this.field_78116_c.rotateAngleX = bipedHead.rotateAngleX;
        this.field_178720_f.rotateAngleY = bipedHead.rotateAngleY;
        this.field_78116_c.rotateAngleY = bipedHead.rotateAngleY;
    }
}
