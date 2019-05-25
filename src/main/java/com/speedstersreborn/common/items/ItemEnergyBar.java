package com.speedstersreborn.common.items;

import com.revivalmodding.revivalcore.core.common.items.ItemEatable;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEnergyBar extends ItemEatable {

    public ItemEnergyBar(String name, int amount, float saturation, boolean isDrink) {
        super(name, amount, saturation, isDrink);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if(entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entityLiving;
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            cap.setHungerTimer((20 * 60) * 20); // 20 Minutes
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
