package com.speedstersreborn.client.models.suits;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Flashs4 - Josh
 * Created using Tabula 7.0.1
 */
public class S4FlashBoots extends ModelBiped {
    public ModelRenderer shape57;
    public ModelRenderer shape59;
    public ModelRenderer shape61;
    public ModelRenderer shape88;
    public ModelRenderer shape87;
    public ModelRenderer shape86;
    public ModelRenderer shape96;
    public ModelRenderer shape95;
    public ModelRenderer shape94;
    public ModelRenderer shape93;
    public ModelRenderer shape56;
    public ModelRenderer shape58;
    public ModelRenderer shape60;
    public ModelRenderer shape85;
    public ModelRenderer shape84;
    public ModelRenderer shape83;
    public ModelRenderer shape91;
    public ModelRenderer shape92;
    public ModelRenderer shape89;
    public ModelRenderer shape90;

    public S4FlashBoots() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.shape90 = new ModelRenderer(this, 85, 95);
        this.shape90.setRotationPoint(-0.1F, 19.9F, 3.1F);
        this.shape90.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1, 0.0F);
        this.shape89 = new ModelRenderer(this, 85, 95);
        this.shape89.setRotationPoint(-0.1F, 19.9F, -0.1F);
        this.shape89.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1, 0.0F);
        this.shape59 = new ModelRenderer(this, 34, 80);
        this.shape59.setRotationPoint(-3.5F, 15.5F, 0.55F);
        this.shape59.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape59, 0.0F, 0.7285004297824331F, -1.1838568316277536F);
        this.shape92 = new ModelRenderer(this, 85, 95);
        this.shape92.setRotationPoint(3.0F, 19.9F, 0.0F);
        this.shape92.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.shape57 = new ModelRenderer(this, 34, 77);
        this.shape57.setRotationPoint(-2.7F, 12.25F, 0.55F);
        this.shape57.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape57, 0.0F, 0.7285004297824331F, 0.091106186954104F);
        this.shape56 = new ModelRenderer(this, 31, 77);
        this.shape56.setRotationPoint(1.2F, 12.4F, 0.55F);
        this.shape56.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape56, 0.0F, 0.7285004297824331F, -0.091106186954104F);
        this.shape91 = new ModelRenderer(this, 85, 95);
        this.shape91.setRotationPoint(-0.2F, 19.9F, 0.0F);
        this.shape91.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.shape93 = new ModelRenderer(this, 85, 92);
        this.shape93.setRotationPoint(-4.0F, 19.9F, -0.1F);
        this.shape93.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1, 0.0F);
        this.shape84 = new ModelRenderer(this, 30, 77);
        this.shape84.setRotationPoint(2.8F, 14.2F, 3.35F);
        this.shape84.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape84, 0.0F, 0.7285004297824331F, 1.1838568316277536F);
        this.shape83 = new ModelRenderer(this, 26, 78);
        this.shape83.setRotationPoint(1.2F, 12.4F, 3.35F);
        this.shape83.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape83, 0.0F, 0.7285004297824331F, -0.091106186954104F);
        this.shape58 = new ModelRenderer(this, 31, 74);
        this.shape58.setRotationPoint(2.8F, 14.2F, 0.55F);
        this.shape58.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape58, 0.0F, 0.7285004297824331F, 1.1838568316277536F);
        this.shape60 = new ModelRenderer(this, 30, 74);
        this.shape60.setRotationPoint(2.4F, 14.8F, 0.55F);
        this.shape60.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape60, 0.0F, 0.7285004297824331F, 0.045553093477052F);
        this.shape86 = new ModelRenderer(this, 32, 76);
        this.shape86.setRotationPoint(-2.7F, 12.25F, 3.35F);
        this.shape86.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape86, 0.0F, 0.7285004297824331F, 0.091106186954104F);
        this.shape88 = new ModelRenderer(this, 36, 75);
        this.shape88.setRotationPoint(-3.9F, 14.8F, 3.35F);
        this.shape88.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape88, 0.0F, 0.7285004297824331F, -0.045553093477052F);
        this.shape87 = new ModelRenderer(this, 32, 78);
        this.shape87.setRotationPoint(-3.5F, 15.5F, 3.35F);
        this.shape87.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.setRotateAngle(shape87, 0.0F, 0.7285004297824331F, -1.1838568316277536F);
        this.shape96 = new ModelRenderer(this, 85, 95);
        this.shape96.setRotationPoint(-4.0F, 19.9F, 0.0F);
        this.shape96.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.shape94 = new ModelRenderer(this, 85, 95);
        this.shape94.setRotationPoint(-4.0F, 19.9F, 3.1F);
        this.shape94.addBox(0.0F, 0.0F, 0.0F, 4, 4, 1, 0.0F);
        this.shape85 = new ModelRenderer(this, 29, 77);
        this.shape85.setRotationPoint(2.4F, 14.8F, 3.35F);
        this.shape85.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape85, 0.0F, 0.7285004297824331F, 0.045553093477052F);
        this.shape95 = new ModelRenderer(this, 85, 95);
        this.shape95.setRotationPoint(-0.8F, 19.9F, 0.0F);
        this.shape95.addBox(0.0F, 0.0F, 0.0F, 1, 4, 4, 0.0F);
        this.shape61 = new ModelRenderer(this, 35, 76);
        this.shape61.setRotationPoint(-3.9F, 14.8F, 0.55F);
        this.shape61.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.0F);
        this.setRotateAngle(shape61, 0.0F, 0.7285004297824331F, -0.045553093477052F);

        // TODO randomly guess which part belongs to the correct leg and add it to that renderer.
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
