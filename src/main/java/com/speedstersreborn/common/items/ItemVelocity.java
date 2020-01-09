package com.speedstersreborn.common.items;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.handlers.EnumHandler.VelocityTypes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemVelocity extends Item {

    VelocityTypes velocityTypes;

    public ItemVelocity(String name, VelocityTypes types) {
        setTranslationKey(name);
        setRegistryName(name);
        setMaxStackSize(1);
        setFull3D();
        setMaxDamage(1);
        this.velocityTypes = types;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        ISpeedsterCap cap = CapabilitySpeedster.get(playerIn);
        PlayerMetaPowerData metaPowerData = CoreCapabilityImpl.getInstance(playerIn).getMetaPowerData();

        if (stack.getTagCompound() == null) {
            stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setBoolean("has_velocity", true);
        }

        if (stack.getTagCompound().getBoolean("has_velocity")) {
            if (!cap.hasVelocity()) {
                if(!playerIn.isCreative()) {
                    setDamage(stack, 1);
                    stack.getTagCompound().setBoolean("has_velocity", false);
                }
                cap.setVelocity(velocityTypes);
                cap.setVelocity(true);
                metaPowerData.setPowerActivated(true);
                cap.sync();
            } else {
            }
        }

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
