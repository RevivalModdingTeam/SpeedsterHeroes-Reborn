package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.client.models.suits.S4FlashBoots;
import com.speedstersreborn.client.models.suits.S4FlashChest;
import com.speedstersreborn.client.models.suits.S4FlashHelmet;
import com.speedstersreborn.client.models.suits.S4FlashLeggings;
import com.speedstersreborn.common.items.SHRItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class ItemFlashSuitS4 extends ItemSuit {

    private S4FlashHelmet helmet = new S4FlashHelmet();
    private S4FlashChest chest = new S4FlashChest();
    private S4FlashLeggings legs = new S4FlashLeggings();
    private S4FlashBoots boots = new S4FlashBoots();

    public ItemFlashSuitS4(String name, ArmorMaterial mat, int i, EntityEquipmentSlot s) {
        super(name, mat, i, s);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return SpeedsterHeroesReborn.MODID + ":textures/suits/flashs4.png";
    }

    @Nullable
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if(!stack.isEmpty()) {
            if(stack.getItem() == SHRItems.s4_flash_helmet) {
                helmet.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                setModelAngles(helmet, _default);
                return helmet;
            } else if(stack.getItem() == SHRItems.s4_flash_leggins) {
                boolean b = armorSlot == EntityEquipmentSlot.LEGS;
                legs.bipedRightLeg.showModel = b;
                legs.bipedLeftLeg.showModel = b;
                setModelAngles(legs, _default);
                return legs;
            }
        }
        return null;
    }

    // TODO: move into core
    public static void setModelAngles(ModelBiped model, ModelBiped parent) {
        model.isChild = parent.isChild;
        model.isRiding = parent.isRiding;
        model.isSneak = parent.isSneak;
        model.rightArmPose = parent.rightArmPose;
        model.leftArmPose = parent.leftArmPose;
    }
}
