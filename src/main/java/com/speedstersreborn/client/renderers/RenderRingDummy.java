package com.speedstersreborn.client.renderers;

import com.revivalmodding.revivalcore.core.client.render.LayerSuitBase;
import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.revivalmodding.revivalcore.util.handlers.client.SuitRenderHandler;
import com.speedstersreborn.common.entity.EntityRingDummy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderRingDummy extends RenderLivingBase<EntityRingDummy> {

    public RenderRingDummy(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelBiped(0), 0.5F);
        this.addLayer(this.new LayerSuitDummy(this));
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

    private class LayerSuitDummy extends LayerSuitBase<EntityRingDummy> {

        private RenderLivingBase renderLivingBase;

        public LayerSuitDummy(RenderLivingBase renderLivingBase) {
            super(renderLivingBase);
            this.renderLivingBase = renderLivingBase;
        }

        @Override
        public void doRenderLayer(EntityRingDummy entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            for(EntityEquipmentSlot slot : SuitRenderHandler.ARMOR) {
                this.renderLayerPart(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, slot);
            }
        }

        private void renderLayerPart(EntityRingDummy entity, float limbSwing, float limbSwingAmount, float partial, float age, float headYaw, float headPitch, float scale, EntityEquipmentSlot slot) {
            ItemSuit suit = this.getSuitFromSlot(entity.suit, slot);
            if(suit == null) return;
            mainModel.setModelAttributes(this.renderLivingBase.getMainModel());
            mainModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partial);
            this.setVisibleParts(slot);
            this.renderLivingBase.bindTexture(suit.get3DTexture());
            mainModel.render(entity, limbSwing, limbSwingAmount, age, headYaw, headPitch, scale);
        }

        private ItemSuit getSuitFromSlot(AbstractSuit suit, EntityEquipmentSlot slot) {
            switch (slot) {
                case HEAD: return suit.getHelmet();
                case CHEST: return suit.getChest();
                case LEGS: default: return suit.getLeggings();
            }
        }

        private void setVisibleParts(EntityEquipmentSlot slot) {
            mainModel.setVisible(false);
            switch (slot) {
                case HEAD: {
                    mainModel.bipedHead.showModel = true;
                    mainModel.bipedHeadwear.showModel = true;
                    break;
                }

                case CHEST: {
                    mainModel.bipedBody.showModel = true;
                    mainModel.bipedRightArm.showModel = true;
                    mainModel.bipedLeftArm.showModel = true;
                    break;
                }

                case LEGS: {
                    mainModel.bipedRightLeg.showModel = true;
                    mainModel.bipedLeftLeg.showModel = true;
                    break;
                }
            }
        }
    }
}
