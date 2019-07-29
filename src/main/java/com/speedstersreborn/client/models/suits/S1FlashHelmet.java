package com.speedstersreborn.client.models.suits;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class S1FlashHelmet extends ModelBiped {

    public ModelRenderer headbase;
    public ModelRenderer shape46;
    public ModelRenderer shape28;
    public ModelRenderer shape29;
    public ModelRenderer shape26;
    public ModelRenderer shape27;
    public ModelRenderer shape24;
    public ModelRenderer shape25;
    public ModelRenderer shape50;
    public ModelRenderer shape49;
    public ModelRenderer shape51;
    public ModelRenderer shape47;
    public ModelRenderer shape48;
    public ModelRenderer shape101;
    public ModelRenderer shape102;
    public ModelRenderer shape100;
    public ModelRenderer shape99;

    public S1FlashHelmet() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.shape24 = new ModelRenderer(this, 80, 0);
        this.shape24.setRotationPoint(1.6F, -7.9F, -1.4F);
        this.shape24.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape24, 0.0F, 0.7285004297824331F, 0.0F);
        this.shape48 = new ModelRenderer(this, 58, 101);
        this.shape48.setRotationPoint(3.5F, -5.4F, 2.8F);
        this.shape48.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, -0.4F);
        this.setRotateAngle(shape48, -0.4553564018453205F, 0.0F, -0.091106186954104F);
        this.shape102 = new ModelRenderer(this, 1, 11);
        this.shape102.setRotationPoint(0.0F, -4.0F, -1.8F);
        this.shape102.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape102, 0.0F, 0.0F, 0.9560913642424937F);
        this.shape25 = new ModelRenderer(this, 80, 0);
        this.shape25.setRotationPoint(-3.2F, -7.9F, -1.4F);
        this.shape25.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape25, 0.0F, 0.7285004297824331F, 0.0F);
        this.shape46 = new ModelRenderer(this, 58, 100);
        this.shape46.setRotationPoint(3.6F, -7.4F, 2.6F);
        this.shape46.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, -0.4F);
        this.setRotateAngle(shape46, -0.4553564018453205F, 0.0F, 0.0F);
        this.shape101 = new ModelRenderer(this, 1, 11);
        this.shape101.setRotationPoint(-0.6F, -3.2F, -1.8F);
        this.shape101.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape101, 0.0F, 0.0F, -0.9560913642424937F);
        this.shape27 = new ModelRenderer(this, 80, 0);
        this.shape27.setRotationPoint(-2.2F, -7.1F, -1.4F);
        this.shape27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape27, 0.0F, 0.7285004297824331F, 0.8651597102135892F);
        this.shape99 = new ModelRenderer(this, 33, 100);
        this.shape99.setRotationPoint(-4.05F, -5.4F, 1.5F);
        this.shape99.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.headbase = new ModelRenderer(this, 0, 0);
        this.headbase.setRotationPoint(0.0F, -8.0F, -2.0F);
        this.headbase.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.shape51 = new ModelRenderer(this, 59, 100);
        this.shape51.setRotationPoint(-4.6F, -5.4F, 2.8F);
        this.shape51.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, -0.4F);
        this.setRotateAngle(shape51, -0.4553564018453205F, 0.0F, 0.0F);
        this.shape100 = new ModelRenderer(this, 35, 100);
        this.shape100.setRotationPoint(3.05F, -5.4F, 1.5F);
        this.shape100.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.shape28 = new ModelRenderer(this, 84, 0);
        this.shape28.setRotationPoint(-2.3F, -6.2F, -1.4F);
        this.shape28.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape28, 0.0F, 0.7285004297824331F, -0.5918411493512771F);
        this.shape49 = new ModelRenderer(this, 57, 99);
        this.shape49.setRotationPoint(-4.6F, -7.4F, 2.6F);
        this.shape49.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, -0.4F);
        this.setRotateAngle(shape49, -0.4553564018453205F, 0.0F, 0.0F);
        this.shape47 = new ModelRenderer(this, 79, 101);
        this.shape47.setRotationPoint(3.5F, -5.4F, 3.6F);
        this.shape47.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.4F);
        this.setRotateAngle(shape47, -1.5707963267948966F, 0.0F, 0.0F);
        this.shape26 = new ModelRenderer(this, 89, 0);
        this.shape26.setRotationPoint(1.1F, -6.0F, -1.4F);
        this.shape26.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape26, 0.0F, 0.7285004297824331F, -0.8651597102135892F);
        this.shape29 = new ModelRenderer(this, 81, 0);
        this.shape29.setRotationPoint(1.0F, -7.0F, -1.4F);
        this.shape29.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape29, 0.0F, 0.7285004297824331F, 0.5918411493512771F);
        this.shape50 = new ModelRenderer(this, 71, 103);
        this.shape50.setRotationPoint(-4.6F, -5.4F, 3.6F);
        this.shape50.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.4F);
        this.setRotateAngle(shape50, -1.5707963267948966F, 0.0F, 0.0F);

        bipedHead.addChild(headbase);
        bipedHead.addChild(shape46);
        bipedHead.addChild(shape24);
        bipedHead.addChild(shape26);
        bipedHead.addChild(shape27);
        bipedHead.addChild(shape28);
        bipedHead.addChild(shape29);
        bipedHead.addChild(shape25);
        bipedHead.addChild(shape47);
        bipedHead.addChild(shape48);
        bipedHead.addChild(shape49);
        bipedHead.addChild(shape50);
        bipedHead.addChild(shape51);
        bipedHead.addChild(shape99);
        bipedHead.addChild(shape100);
        bipedHead.addChild(shape101);
        bipedHead.addChild(shape102);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
