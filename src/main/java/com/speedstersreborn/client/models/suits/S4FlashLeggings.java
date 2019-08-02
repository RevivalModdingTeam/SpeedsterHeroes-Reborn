package com.speedstersreborn.client.models.suits;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Flashs4 - Josh
 * Created using Tabula 7.0.1
 */
public class S4FlashLeggings extends ModelBiped {
    public ModelRenderer rightlegbase;
    public ModelRenderer leftlegbase;

    public S4FlashLeggings() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.rightlegbase = new ModelRenderer(this, 83, 68);
        this.rightlegbase.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.rightlegbase.addBox(-2.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.leftlegbase = new ModelRenderer(this, 84, 67);
        this.leftlegbase.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.leftlegbase.addBox(-2.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);

        this.bipedRightLeg.addChild(rightlegbase);
        this.bipedLeftLeg.addChild(leftlegbase);
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
    }
}
