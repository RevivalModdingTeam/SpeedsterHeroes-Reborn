package com.speedstersreborn.client.models.suits;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

/**
 * Flashs4 - Josh
 * Created using Tabula 7.0.1
 */
public class S4FlashHelmet extends ModelBiped {
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

    public S4FlashHelmet() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.shape99 = new ModelRenderer(this, 85, 94);
        this.shape99.setRotationPoint(-4.05F, -5.4F, 1.5F);
        this.shape99.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.shape48 = new ModelRenderer(this, 28, 72);
        this.shape48.setRotationPoint(3.5F, -5.4F, 2.8F);
        this.shape48.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, -0.4F);
        this.setRotateAngle(shape48, -0.4553564018453205F, 0.0F, -0.091106186954104F);
        this.shape27 = new ModelRenderer(this, 84, 95);
        this.shape27.setRotationPoint(-2.2F, -7.1F, -1.4F);
        this.shape27.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape27, 0.0F, 0.7285004297824331F, 0.8651597102135892F);
        this.shape47 = new ModelRenderer(this, 27, 70);
        this.shape47.setRotationPoint(3.5F, -5.4F, 3.6F);
        this.shape47.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.4F);
        this.setRotateAngle(shape47, -1.5707963267948966F, 0.0F, 0.0F);
        this.shape25 = new ModelRenderer(this, 87, 91);
        this.shape25.setRotationPoint(-3.2F, -7.9F, -1.4F);
        this.shape25.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape25, 0.0F, 0.7285004297824331F, 0.0F);
        this.shape46 = new ModelRenderer(this, 28, 70);
        this.shape46.setRotationPoint(3.6F, -7.4F, 2.6F);
        this.shape46.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, -0.4F);
        this.setRotateAngle(shape46, -0.4553564018453205F, 0.0F, 0.0F);
        this.shape26 = new ModelRenderer(this, 85, 95);
        this.shape26.setRotationPoint(1.1F, -6.0F, -1.4F);
        this.shape26.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape26, 0.0F, 0.7285004297824331F, -0.8651597102135892F);
        this.shape28 = new ModelRenderer(this, 85, 95);
        this.shape28.setRotationPoint(-2.3F, -6.2F, -1.4F);
        this.shape28.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape28, 0.0F, 0.7285004297824331F, -0.5918411493512771F);
        this.shape50 = new ModelRenderer(this, 32, 74);
        this.shape50.setRotationPoint(-4.6F, -5.4F, 3.6F);
        this.shape50.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.4F);
        this.setRotateAngle(shape50, -1.5707963267948966F, 0.0F, 0.0F);
        this.shape101 = new ModelRenderer(this, 83, 69);
        this.shape101.setRotationPoint(-0.6F, -3.2F, -1.8F);
        this.shape101.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape101, 0.0F, 0.0F, -0.9560913642424937F);
        this.shape29 = new ModelRenderer(this, 85, 65);
        this.shape29.setRotationPoint(1.0F, -7.0F, -1.4F);
        this.shape29.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape29, 0.0F, 0.7285004297824331F, 0.5918411493512771F);
        this.headbase = new ModelRenderer(this, 96, 71);
        this.headbase.setRotationPoint(0.0F, -8.0F, -2.0F);
        this.headbase.addBox(-4.0F, 0.0F, 0.0F, 8, 8, 8, 0.0F);
        this.shape51 = new ModelRenderer(this, 29, 70);
        this.shape51.setRotationPoint(-4.6F, -5.4F, 2.8F);
        this.shape51.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, -0.4F);
        this.setRotateAngle(shape51, -0.4553564018453205F, 0.0F, 0.0F);
        this.shape24 = new ModelRenderer(this, 85, 95);
        this.shape24.setRotationPoint(1.6F, -7.9F, -1.4F);
        this.shape24.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape24, 0.0F, 0.7285004297824331F, 0.0F);
        this.shape100 = new ModelRenderer(this, 87, 95);
        this.shape100.setRotationPoint(3.05F, -5.4F, 1.5F);
        this.shape100.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        this.shape102 = new ModelRenderer(this, 85, 68);
        this.shape102.setRotationPoint(0.0F, -4.0F, -1.8F);
        this.shape102.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.setRotateAngle(shape102, 0.0F, 0.0F, 0.9560913642424937F);
        this.shape49 = new ModelRenderer(this, 29, 70);
        this.shape49.setRotationPoint(-4.6F, -7.4F, 2.6F);
        this.shape49.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, -0.4F);
        this.setRotateAngle(shape49, -0.4553564018453205F, 0.0F, 0.0F);

        this.bipedHead.addChild(headbase);
        this.bipedHead.addChild(shape46);
        this.bipedHead.addChild(shape28);
        this.bipedHead.addChild(shape29);
        this.bipedHead.addChild(shape26);
        this.bipedHead.addChild(shape27);
        this.bipedHead.addChild(shape24);
        this.bipedHead.addChild(shape25);
        this.bipedHead.addChild(shape50);
        this.bipedHead.addChild(shape49);
        this.bipedHead.addChild(shape51);
        this.bipedHead.addChild(shape47);
        this.bipedHead.addChild(shape48);
        this.bipedHead.addChild(shape101);
        this.bipedHead.addChild(shape102);
        this.bipedHead.addChild(shape100);
        this.bipedHead.addChild(shape99);

        bipedHead.childModels.forEach(c -> c.offsetZ = -0.13F);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(1.1F, 1.1F, 1.1F);
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        GlStateManager.popMatrix();
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
