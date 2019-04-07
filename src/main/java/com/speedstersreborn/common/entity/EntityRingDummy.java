package com.speedstersreborn.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class EntityRingDummy extends EntityLivingBase {

    public float dirX;
    public float dirZ;

    public NonNullList<ItemStack> inventory = NonNullList.withSize(4, ItemStack.EMPTY);

    public EntityRingDummy(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        this.rotationYawHead = rotationYaw;

        if (ticksExisted < 20) {
            this.motionX = dirX / 10F;
            this.motionY = 0;
            this.motionZ = dirZ / 10F;
        }
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player) {
        super.onCollideWithPlayer(player);

        if (ticksExisted < 30 || player.world.isRemote)
            return;

        double distance = player.getDistanceSq(this);
        if (distance > 0.5D)
            return;

        if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty())
            player.setItemStackToSlot(EntityEquipmentSlot.HEAD, getItemStackFromSlot(EntityEquipmentSlot.HEAD));
        else
            player.inventory.addItemStackToInventory(getItemStackFromSlot(EntityEquipmentSlot.HEAD));

        if (player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty())
            player.setItemStackToSlot(EntityEquipmentSlot.CHEST, getItemStackFromSlot(EntityEquipmentSlot.CHEST));
        else
            player.inventory.addItemStackToInventory(getItemStackFromSlot(EntityEquipmentSlot.CHEST));

        if (player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).isEmpty())
            player.setItemStackToSlot(EntityEquipmentSlot.LEGS, getItemStackFromSlot(EntityEquipmentSlot.LEGS));
        else
            player.inventory.addItemStackToInventory(getItemStackFromSlot(EntityEquipmentSlot.LEGS));

        if (player.getItemStackFromSlot(EntityEquipmentSlot.FEET).isEmpty())
            player.setItemStackToSlot(EntityEquipmentSlot.FEET, getItemStackFromSlot(EntityEquipmentSlot.FEET));
        else
            player.inventory.addItemStackToInventory(getItemStackFromSlot(EntityEquipmentSlot.FEET));

        this.setDead();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);

        NBTTagList nbtList = new NBTTagList();

        for (int i = 0; i < 4; i++) {
            ItemStack s = inventory.get(i);
            if (!s.isEmpty()) {
                NBTTagCompound nbt = new NBTTagCompound();
                nbt.setInteger("Slot", i);
                s.writeToNBT(nbt);
                nbtList.appendTag(nbt);
            }
        }

        tagCompound.setTag("ItemInventory", nbtList);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);

        NBTTagList items = tagCompund.getTagList("ItemInventory", Constants.NBT.TAG_COMPOUND);
        NonNullList<ItemStack> armor = NonNullList.withSize(4, ItemStack.EMPTY);

        for (int i = 0; i < 4; i++) {
            NBTTagCompound item = items.getCompoundTagAt(i);
            int slot = item.getInteger("Slot");

            if (slot >= 0 && slot < 4) {
                armor.set(slot, new ItemStack(item));
            }
        }

        this.inventory = armor;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return this.inventory;
    }

    @Override
    public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
        return slotIn.getSlotType() == EntityEquipmentSlot.Type.ARMOR ? this.inventory.get(slotIn.getIndex()) : ItemStack.EMPTY;
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {
        if (slotIn.getSlotType() == EntityEquipmentSlot.Type.ARMOR && this.inventory.get(slotIn.getIndex()) == ItemStack.EMPTY) {
            this.playEquipSound(stack);
            this.inventory.set(slotIn.getIndex(), stack);
        }
    }

    @Override
    public EnumHandSide getPrimaryHand() {
        return EnumHandSide.RIGHT;
    }

}
