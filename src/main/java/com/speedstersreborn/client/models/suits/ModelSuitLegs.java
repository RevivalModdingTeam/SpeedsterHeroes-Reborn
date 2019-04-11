package com.speedstersreborn.client.models.suits;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * ModelPlayerAlt - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
public class ModelSuitLegs extends ModelBiped {
    public ModelRenderer field_178731_d;
    public ModelRenderer field_178721_j;
    public ModelRenderer field_178733_c;
    public ModelRenderer field_178722_k;
    public ModelBiped modelBiped;

    public ModelSuitLegs(ModelBiped biped) {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.modelBiped = biped;
        this.field_178731_d = new ModelRenderer(this, 0, 32);
        this.field_178731_d.setRotationPoint(-1.899999976158142F, 12.0F, 0.0F);
        this.field_178731_d.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.field_178722_k = new ModelRenderer(this, 16, 48);
        this.field_178722_k.setRotationPoint(1.899999976158142F, 12.0F, 0.0F);
        this.field_178722_k.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.field_178721_j = new ModelRenderer(this, 0, 16);
        this.field_178721_j.setRotationPoint(-1.899999976158142F, 12.0F, 0.0F);
        this.field_178721_j.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.field_178733_c = new ModelRenderer(this, 0, 48);
        this.field_178733_c.setRotationPoint(1.899999976158142F, 12.0F, 0.0F);
        this.field_178733_c.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GlStateManager.pushMatrix();
        if (entity != null && entity.isSneaking()) {
            GlStateManager.translate(0, 0, 0.25);
        }
        this.field_178731_d.render(f5);
        this.field_178721_j.render(f5);
        this.field_178722_k.render(f5);
        this.field_178733_c.render(f5);
        GlStateManager.popMatrix();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw,  headPitch, scaleFactor, entityIn);
        this.field_178731_d.rotateAngleX = bipedRightLeg.rotateAngleX;
        this.field_178721_j.rotateAngleX = bipedRightLeg.rotateAngleX;
        this.field_178733_c.rotateAngleX = bipedLeftLeg.rotateAngleX;
        this.field_178722_k.rotateAngleX = bipedLeftLeg.rotateAngleX;
    }
}
