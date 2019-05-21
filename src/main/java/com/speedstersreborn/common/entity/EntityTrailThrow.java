package com.speedstersreborn.common.entity;

import com.speedstersreborn.util.handlers.client.TrailRenderHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Random;

public class EntityTrailThrow extends EntityThrowable {

    public EntityPlayer owner;
    public TrailRenderHandler.TrailType trailType;
    public float[] lightningFactor;
    public float alpha = 1F;

    public EntityTrailThrow(World worldIn) {
        super(worldIn);
        noClip = true;
        ignoreFrustumCheck = true;
    }

    public EntityTrailThrow(World world, EntityPlayer owner, TrailRenderHandler.TrailType trailType) {
        this(world);
        this.owner = owner;
        this.trailType = trailType;
        this.setLocationAndAngles(owner.posX, owner.posY, owner.posZ, owner.rotationYaw, owner.rotationPitch);
        this.setSize(owner.width, owner.height);

        this.prevRotationPitch = owner.rotationPitch;
        this.rotationPitch = owner.rotationPitch;

        this.lightningFactor = new float[20];
        for (int i = 0; i < 20; i++) {
            this.lightningFactor[i] = rand.nextFloat();
        }
    }

    public Random getRandom() {
        return new Random(this.getEntityId());
    }

    public Vec3d getLightningPosVector(int i) {
        float halfWidth = width / 2;
        return new Vec3d(posX - halfWidth + (lightningFactor[i] * width), posY, posZ - halfWidth + (lightningFactor[10 + i] * width));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (this.ticksExisted >= 11) {
            this.setDead();
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if(world.isRemote)
            return;

        if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
            this.setDead();
        }
    }


    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 1;
    }

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public boolean writeToNBTOptional(NBTTagCompound par1NBTTagCompound) {
        return false;
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return Arrays.asList();
    }

    @Override
    public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

    }

}
