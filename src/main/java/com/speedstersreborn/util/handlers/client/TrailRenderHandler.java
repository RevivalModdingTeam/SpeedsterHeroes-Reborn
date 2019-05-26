package com.speedstersreborn.util.handlers.client;

import com.google.common.collect.Maps;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.List;
import java.util.*;

@Mod.EventBusSubscriber(modid = SpeedsterHeroesReborn.MODID, value = Side.CLIENT)
public class TrailRenderHandler {

    private static Map<EntityPlayer, LinkedList<EntityTrail>> TRAIL_ENTITIES = Maps.newHashMap();
    private static Map<EntityPlayer, LinkedList<EntityTrailSecond>> TRAIL_SECOND = Maps.newHashMap();
    public static HashMap<EntityLivingBase, Vec3d> LAST_POS_MAP = Maps.newHashMap();

    @SubscribeEvent
    public static void onLeave(PlayerEvent.PlayerLoggedOutEvent e) {
        if (TRAIL_ENTITIES.containsKey(e.player))
            TRAIL_ENTITIES.remove(e.player);
        if (TRAIL_SECOND.containsKey(e.player))
            TRAIL_SECOND.remove(e.player);
    }

    @SubscribeEvent
    public static void onDeath(PlayerEvent.PlayerRespawnEvent e) {
        if (TRAIL_ENTITIES.containsKey(e.player)) {
            TRAIL_ENTITIES.remove(e.player);
        }
        if (TRAIL_SECOND.containsKey(e.player))
            TRAIL_SECOND.remove(e.player);
    }

    @SubscribeEvent
    public static void onDimensionChange(PlayerEvent.PlayerChangedDimensionEvent e) {
        if (TRAIL_ENTITIES.containsKey(e.player)) {
            TRAIL_ENTITIES.remove(e.player);
        }
        if (TRAIL_SECOND.containsKey(e.player))
            TRAIL_SECOND.remove(e.player);
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.phase == TickEvent.Phase.END && e.player.world.isRemote) {
            ISpeedsterCap cap = CapabilitySpeedster.get(e.player);
            LinkedList<EntityTrail> trails = TRAIL_ENTITIES.containsKey(e.player) ? TRAIL_ENTITIES.get(e.player) : new LinkedList<>();
            LinkedList<EntityTrailSecond> trailsS = TRAIL_SECOND.containsKey(e.player) ? TRAIL_SECOND.get(e.player) : new LinkedList<>();
            boolean b = false;
            List<EntityTrail> toDelete = new LinkedList<>();
            List<EntityTrailSecond> toSecondDelete = new LinkedList<>();

            for (EntityTrail trail : trails) {
                if (trail.isDead || trail.dimension != e.player.dimension || trail.getDistance(e.player) >= 2 * cap.getSpeedLevel()) {
                    toDelete.add(trail);
                }
            }
            toDelete.forEach(t -> trails.remove(t));

            for (EntityTrailSecond trailSecond : trailsS) {
                if (trailSecond.isDead || trailSecond.dimension != e.player.dimension || trailSecond.getDistance(e.player) >= 2 * cap.getSpeedLevel()) {
                    toSecondDelete.add(trailSecond);
                }
            }
            toSecondDelete.forEach(t -> trailsS.remove(t));

            if (b) {
                TRAIL_ENTITIES.put(e.player, trails);
                TRAIL_SECOND.put(e.player, trailsS);
            }

            if (renderTrail(e.player)) {
                EntityPlayer player = e.player;

                if (trails.isEmpty() || trails.getLast().getDistance(player) >= player.width * 1.1f) {
                    EntityTrail esm = new EntityTrail(player.world, player, getTrailType(player));
                    player.world.spawnEntity(esm);
                    addTrailEntity(player, esm);
                }

                if (renderSecondTrail(player)) {

                    if (trailsS.isEmpty() || trailsS.getLast().getDistance(player) >= player.width * 1.1f) {
                        EntityTrailSecond esm = new EntityTrailSecond(player.world, player, getTrailType(player));
                        player.world.spawnEntity(esm);
                        addSecondTrailEntity(player, esm);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void renderEntityPost(RenderPlayerEvent.Post e) {
        TrailType trailType = getTrailType(e.getEntityPlayer());
        if (trailType != null) {
            trailType.getTrailRenderer().renderTrail(e.getEntityPlayer(), trailType, getTrailEntities(e.getEntityPlayer()), e.getPartialRenderTick());
            trailType.getTrailRenderer().renderSecondTrail(e.getEntityPlayer(), trailType, getSecondTrailEntities(e.getEntityPlayer()), e.getPartialRenderTick());
        }
    }

    /**
     * Determines if the trail should be rendered
     *
     * @param player
     * @return
     */
    private static boolean renderTrail(EntityPlayer player) {
        ISpeedsterCap cap = CapabilitySpeedster.get(player);
        return cap.isSpeedster() || cap.hasVelocity();
    }

    private static boolean renderSecondTrail(EntityPlayer player) {
        ISpeedsterCap cap = CapabilitySpeedster.get(player);
        if (cap.isSpeedster() || cap.hasVelocity()) {
            return cap.hasSecondTrail();
        }
        return false;
    }

    /**
     * Determines the trail type of the player
     *
     * @param player
     * @return
     */
    public static TrailType getTrailType(EntityPlayer player) {
        return TrailType.LIGHTNINGS;
    }

    /**
     * Determines the color of the players trail
     *
     * @param player
     * @param trailType
     * @return
     */
    private static Color getTrailColor(EntityPlayer player, TrailType trailType) {
        return CapabilitySpeedster.get(player).getPrimaryTrailColor();
    }

    private static Color getSecondTrailColor(EntityPlayer player, TrailType trailType) {
        return CapabilitySpeedster.get(player).getSecondaryTrailColor();
    }

    private static LinkedList<EntityTrail> getTrailEntities(EntityPlayer player) {
        return TRAIL_ENTITIES.containsKey(player) ? TRAIL_ENTITIES.get(player) : new LinkedList<>();
    }

    private static LinkedList<EntityTrailSecond> getSecondTrailEntities(EntityPlayer player) {
        return TRAIL_SECOND.containsKey(player) ? TRAIL_SECOND.get(player) : new LinkedList<>();
    }

    private static void addTrailEntity(EntityPlayer player, EntityTrail trail) {
        LinkedList<EntityTrail> trails = TRAIL_ENTITIES.containsKey(player) ? TRAIL_ENTITIES.get(player) : new LinkedList<>();
        trails.add(trail);
        TRAIL_ENTITIES.put(player, trails);
    }

    private static void addSecondTrailEntity(EntityPlayer player, EntityTrailSecond trail) {
        LinkedList<EntityTrailSecond> trails = TRAIL_SECOND.containsKey(player) ? TRAIL_SECOND.get(player) : new LinkedList<>();
        trails.add(trail);
        TRAIL_SECOND.put(player, trails);
    }

    public enum TrailType {

        LIGHTNINGS,
        NORMAL,
        PARTICLES,
        ELECTRICITY;

        @SideOnly(Side.CLIENT)
        public TrailRenderer getTrailRenderer() {
            if (this == LIGHTNINGS)
                return new TrailRendererLightnings();
            if (this == NORMAL)
                return new TrailRendererNormal();
            if (this == PARTICLES)
                return new TrailRendererParticles();
            if (this == ELECTRICITY)
                return new TrailRendererRandomLightnings();
            return null;
        }

        public static TrailType getTrailTypeFromName(String name) {
            for (TrailType types : values()) {
                if (types.toString().equalsIgnoreCase(name)) {
                    return types;
                }
            }
            return null;
        }

    }

    public static class EntityTrail extends EntityLivingBase {

        public EntityPlayer owner;
        public TrailType trailType;
        public float[] lightningFactor;
        public float alpha = 1F;

        public EntityTrail(World worldIn) {
            super(worldIn);
            noClip = true;
            ignoreFrustumCheck = true;
        }

        public EntityTrail(World world, EntityPlayer owner, TrailType trailType) {
            this(world);
            this.owner = owner;
            this.trailType = trailType;
            this.setLocationAndAngles(owner.posX, owner.posY, owner.posZ, owner.rotationYaw, owner.rotationPitch);
            this.setSize(owner.width, owner.height);

            this.swingProgress = owner.swingProgress;
            this.prevSwingProgress = owner.swingProgress;
            this.prevRenderYawOffset = owner.renderYawOffset;
            this.renderYawOffset = owner.renderYawOffset;
            this.prevRotationYawHead = owner.rotationYawHead;
            this.rotationYawHead = owner.rotationYawHead;
            this.prevRotationPitch = owner.rotationPitch;
            this.rotationPitch = owner.rotationPitch;
            this.limbSwingAmount = owner.limbSwingAmount;
            this.prevLimbSwingAmount = owner.limbSwingAmount;
            this.limbSwing = owner.limbSwing;

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
        public void onLivingUpdate() {

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
        public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
            return ItemStack.EMPTY;
        }

        @Override
        public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

        }

        @Override
        public EnumHandSide getPrimaryHand() {
            return owner.getPrimaryHand();
        }

    }

    public static class EntityTrailSecond extends EntityLivingBase {

        public EntityPlayer owner;
        public TrailType trailType;
        public float[] lightningFactor;
        public float alpha = 1F;

        public EntityTrailSecond(World worldIn) {
            super(worldIn);
            noClip = true;
            ignoreFrustumCheck = true;
        }

        public EntityTrailSecond(World world, EntityPlayer owner, TrailType trailType) {
            this(world);
            this.owner = owner;
            this.trailType = trailType;
            this.setLocationAndAngles(owner.posX, owner.posY, owner.posZ, owner.rotationYaw, owner.rotationPitch);
            this.setSize(owner.width, owner.height);

            this.swingProgress = owner.swingProgress;
            this.prevSwingProgress = owner.swingProgress;
            this.prevRenderYawOffset = owner.renderYawOffset;
            this.renderYawOffset = owner.renderYawOffset;
            this.prevRotationYawHead = owner.rotationYawHead;
            this.rotationYawHead = owner.rotationYawHead;
            this.prevRotationPitch = owner.rotationPitch;
            this.rotationPitch = owner.rotationPitch;
            this.limbSwingAmount = owner.limbSwingAmount;
            this.prevLimbSwingAmount = owner.limbSwingAmount;
            this.limbSwing = owner.limbSwing;

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
        public void onLivingUpdate() {

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
        public ItemStack getItemStackFromSlot(EntityEquipmentSlot slotIn) {
            return ItemStack.EMPTY;
        }

        @Override
        public void setItemStackToSlot(EntityEquipmentSlot slotIn, ItemStack stack) {

        }

        @Override
        public EnumHandSide getPrimaryHand() {
            return owner.getPrimaryHand();
        }

    }

    @SideOnly(Side.CLIENT)
    public static abstract class TrailRenderer {

        @SideOnly(Side.CLIENT)
        public abstract void renderTrail(EntityPlayer player, TrailType trail, LinkedList<EntityTrail> trailEntities, float partialRenderTicks);

        @SideOnly(Side.CLIENT)
        public abstract void renderSecondTrail(EntityPlayer player, TrailType trail, LinkedList<EntityTrailSecond> trailEntities, float partialRenderTicks);


        public boolean preRenderSpeedMirage(EntityTrail entity, TrailType trail, float partialRenderTicks) {
            return false;
        }

        public boolean preRenderSecondSpeedMirage(EntityTrailSecond entity, TrailType trail, float partialRenderTicks) {
            return false;
        }

    }

    @SideOnly(Side.CLIENT)
    public static class TrailRendererLightnings extends TrailRenderer {

        public static int lineWidth = 5;
        public static int innerLineWidth = 1;

        @Override
        public void renderTrail(EntityPlayer player, TrailType trail, LinkedList<EntityTrail> trailEntities, float partialRenderTicks) {
            if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && player == Minecraft.getMinecraft().player)
                return;

            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 1);
            setLightmapTextureCoords(240, 240);
            EntityPlayer mcPlayer = Minecraft.getMinecraft().player;
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            translateRendering(mcPlayer, player, partialRenderTicks + getOffsetFunctionSpeed(cap.getSpeedLevel()));

            int amountOfLightnings = 10;
            float lightningSpace = player.height / amountOfLightnings;

            for (int j = 0; j < amountOfLightnings; j++) {
                // 10 Blitze ----------------------------------------------
                Vec3d add = new Vec3d(0, j * lightningSpace, 0);
                float differ = 0.425F * (player.height / 1.8F);
                if (trailEntities.size() > 0) {

                    Vec3d firstStart = (trailEntities.getLast().getLightningPosVector(j)
                            .subtract(trailEntities.getLast().getPositionEyes(partialRenderTicks)))
                            .add((player.getPositionEyes(partialRenderTicks).add(0.0D, -1.62F * (player.height / 1.8F), 0.0D)));
                    Vec3d firstEnd = trailEntities.getLast().getLightningPosVector(j);
                    float a = 1F - (trailEntities.getLast().ticksExisted + partialRenderTicks) / 10F;
                    drawLine(firstStart.add(add).add(0, player.height, 0),
                            firstEnd.add(add.add(0, trailEntities.getLast().lightningFactor[j] * differ, 0)), lineWidth, innerLineWidth,
                            getTrailColor(player, trail), a);

                    for (int i = 0; i < trailEntities.size(); i++) {
                        if (i < (trailEntities.size() - 1)) {
                            EntityTrail speedMirage = trailEntities.get(i);
                            EntityTrail speedMirage2 = trailEntities.get(i + 1);
                            Vec3d start = speedMirage.getLightningPosVector(j);
                            Vec3d end = speedMirage2.getLightningPosVector(j);
                            float progress = 1F - (speedMirage.ticksExisted + partialRenderTicks) / 10F;
                            drawLine(start.add(add.add(0, speedMirage.lightningFactor[j] * differ, 0)),
                                    end.add(add.add(0, speedMirage2.lightningFactor[j] * differ, 0)), 5, 1, getTrailColor(player, trail), progress);
                        }
                    }
                }
            }
            GlStateManager.color(1, 1, 1, 1);
            restoreLightmapTextureCoords();
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }

        @Override
        public void renderSecondTrail(EntityPlayer player, TrailType trail, LinkedList<EntityTrailSecond> trailEntities, float partialRenderTicks) {
            if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && player == Minecraft.getMinecraft().player)
                return;

            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 1);
            setLightmapTextureCoords(240, 240);
            EntityPlayer mcPlayer = Minecraft.getMinecraft().player;
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            translateRendering(mcPlayer, player, partialRenderTicks + getOffsetFunctionSpeed(cap.getSpeedLevel()));

            int amountOfLightnings = 6;
            float lightningSpace = player.height / amountOfLightnings;

            for (int j = 0; j < amountOfLightnings; j++) {
                // 10 Blitze ----------------------------------------------
                Vec3d add = new Vec3d(0, j * lightningSpace, 0);
                float differ = 0.425F * (player.height / 1.8F);
                if (trailEntities.size() > 0) {

                    Vec3d firstStart = (trailEntities.getLast().getLightningPosVector(j)
                            .subtract(trailEntities.getLast().getPositionEyes(partialRenderTicks)))
                            .add((player.getPositionEyes(partialRenderTicks).add(0.0D, -1.62F * (player.height / 1.8F), 0.0D)));
                    Vec3d firstEnd = trailEntities.getLast().getLightningPosVector(j);
                    float a = 1F - (trailEntities.getLast().ticksExisted + partialRenderTicks) / 10F;
                    drawLine(firstStart.add(add).add(0, player.height, 0),
                            firstEnd.add(add.add(0, trailEntities.getLast().lightningFactor[j] * differ, 0)), lineWidth, innerLineWidth,
                            getSecondTrailColor(player, trail), a);

                    for (int i = 0; i < trailEntities.size(); i++) {
                        if (i < (trailEntities.size() - 1)) {
                            EntityTrailSecond speedMirage = trailEntities.get(i);
                            EntityTrailSecond speedMirage2 = trailEntities.get(i + 1);
                            Vec3d start = speedMirage.getLightningPosVector(j);
                            Vec3d end = speedMirage2.getLightningPosVector(j);
                            float progress = 1F - (speedMirage.ticksExisted + partialRenderTicks) / 10F;
                            drawLine(start.add(add.add(0, speedMirage.lightningFactor[j] * differ, 0)),
                                    end.add(add.add(0, speedMirage2.lightningFactor[j] * differ, 0)), 5, 1, getSecondTrailColor(player, trail), progress);
                        }
                    }
                }
            }
            GlStateManager.color(1, 1, 1, 1);
            restoreLightmapTextureCoords();
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }

        private float getOffsetFunctionSpeed(int level) {
            switch (level) {
                case 0:
                    return 0.8f;
                case 1:
                    return 1.0f;
                case 2:
                    return 0.94f;
                case 3:
                    return 0.96f;
                case 4:
                    return 1f;
                case 5:
                    return 0.9f;
                case 6:
                    return 0.9f;
                case 7:
                    return 0.9f;
                case 9:
                    return 0.9f;
                case 15:
                    return 0.95f;
                case 16:
                    return 0.95f;
                case 17:
                    return 0.91f;
            }
            return 0.94f;
        }
    }

    public static class TrailRendererNormal extends TrailRenderer {

        @Override
        public void renderTrail(EntityPlayer player, TrailType trail, LinkedList<EntityTrail> trailEntities, float partialRenderTicks) {

        }

        @Override
        public void renderSecondTrail(EntityPlayer player, TrailType trail, LinkedList<EntityTrailSecond> trailEntities, float partialRenderTicks) {

        }

        @Override
        public boolean preRenderSpeedMirage(EntityTrail entity, TrailType trail, float partialRenderTicks) {
            Color c = getTrailColor(entity.owner, trail);
            float progress = MathHelper.clamp(1F - (entity.ticksExisted + partialRenderTicks) / 10F, 0F, 0.5F);
            float translate = -MathHelper.clamp(1F - (entity.ticksExisted) / 10F, 0F, 0.5F) / 15F;
            GlStateManager.translate(0F, translate * (entity.height / 1.8F), 0F);
            GL11.glBlendFunc(770, 771);
            GL11.glAlphaFunc(516, 0.003921569F);
            GlStateManager.color((float) c.getRed() / 255F, (float) c.getGreen() / 255F, (float) c.getBlue() / 255F, progress);
            entity.alpha = progress;
            return true;
        }

        @Override
        public boolean preRenderSecondSpeedMirage(EntityTrailSecond entity, TrailType trail, float partialRenderTicks) {
            Color c = getSecondTrailColor(entity.owner, trail);
            float progress = MathHelper.clamp(1F - (entity.ticksExisted + partialRenderTicks) / 10F, 0F, 0.5F);
            float translate = -MathHelper.clamp(1F - (entity.ticksExisted) / 10F, 0F, 0.5F) / 15F;
            GlStateManager.translate(0F, translate * (entity.height / 1.8F), 0F);
            GL11.glBlendFunc(770, 771);
            GL11.glAlphaFunc(516, 0.003921569F);
            GlStateManager.color((float) c.getRed() / 255F, (float) c.getGreen() / 255F, (float) c.getBlue() / 255F, progress);
            entity.alpha = progress;
            return true;
        }
    }

    public static class TrailRendererParticles extends TrailRenderer {

        @Override
        public boolean preRenderSpeedMirage(EntityTrail entity, TrailType trail, float partialRenderTicks) {
            Color c = getTrailColor(entity.owner, trail);
            float progress = MathHelper.clamp(1F - (entity.ticksExisted + partialRenderTicks) / 10F, 0F, 0.2F);
            float translate = -MathHelper.clamp(1F - (entity.ticksExisted) / 10F, 0F, 0.5F) / 15F;
            float scale = entity.height / 1.8F;
            GlStateManager.translate(0F, translate * scale, 0F);
            GL11.glBlendFunc(770, 771);
            GL11.glAlphaFunc(516, 0.003921569F);
            GlStateManager.color((float) c.getRed() / 255F, (float) c.getGreen() / 255F, (float) c.getBlue() / 255F, progress);
            entity.alpha = progress;
            return true;
        }

        @Override
        public boolean preRenderSecondSpeedMirage(EntityTrailSecond entity, TrailType trail, float partialRenderTicks) {
            Color c = getSecondTrailColor(entity.owner, trail);
            float progress = MathHelper.clamp(1F - (entity.ticksExisted + partialRenderTicks) / 10F, 0F, 0.2F);
            float translate = -MathHelper.clamp(1F - (entity.ticksExisted) / 10F, 0F, 0.5F) / 15F;
            float scale = entity.height / 1.8F;
            GlStateManager.translate(0F, translate * scale, 0F);
            GL11.glBlendFunc(770, 771);
            GL11.glAlphaFunc(516, 0.003921569F);
            GlStateManager.color((float) c.getRed() / 255F, (float) c.getGreen() / 255F, (float) c.getBlue() / 255F, progress);
            entity.alpha = progress;
            return true;
        }

        @SideOnly(Side.CLIENT)
        public void drawInnerLight(Vec3d start, Vec3d end, float height, Color c, float alpha) {
            Tessellator tes = Tessellator.getInstance();
            BufferBuilder wr = tes.getBuffer();

            float a = MathHelper.clamp(alpha, 0F, 0.25F);
            GlStateManager.color((float) c.getRed() / 255F, (float) c.getGreen() / 255F, (float) c.getBlue() / 255F, a);

            wr.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);

            wr.pos(start.x, start.y, start.z).endVertex();
            wr.pos(start.x, start.y + height, start.z).endVertex();
            wr.pos(end.x, end.y + height, end.z).endVertex();
            wr.pos(end.x, end.y, end.z).endVertex();

            wr.pos(end.x, end.y, end.z).endVertex();
            wr.pos(end.x, end.y + height, end.z).endVertex();
            wr.pos(start.x, start.y + height, start.z).endVertex();
            wr.pos(start.x, start.y, start.z).endVertex();

            tes.draw();
        }

        @Override
        public void renderTrail(EntityPlayer player, TrailType trail, LinkedList<EntityTrail> list, float partialRenderTicks) {
            if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && player == Minecraft.getMinecraft().player)
                return;

            Color c = getTrailColor(player, trail);

            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 1);
            translateRendering(Minecraft.getMinecraft().player, player, partialRenderTicks);

            int amountOfLightnings = 7;
            float lightningSpace = player.height / amountOfLightnings;

            if (list.size() > 0) {
                for (int j = 0; j < amountOfLightnings; j++) {
                    // 10 Blitze ----------------------------------------------
                    Vec3d add = new Vec3d(0, j * lightningSpace, 0);

                    Vec3d firstStart = (list.getLast().getLightningPosVector(j)
                            .subtract(list.getLast().getPositionEyes(partialRenderTicks)))
                            .add((player.getPositionEyes(partialRenderTicks).add(0.0D, -1.62F * (player.height / 1.8F), 0.0D)));
                    Vec3d firstEnd = list.getLast().getLightningPosVector(j);
                    float a = 1F - (list.getLast().ticksExisted + partialRenderTicks) / 10F;
                    drawLine(firstStart.add(add.add(0, player.height, 0)), firstEnd.add(add), 2, 1,
                            list.getLast().lightningFactor[j] < 0.5F ? c : new Color(0.6F * 255, 0.6F * 255, 0.6F * 255), a);

                    for (int i = 0; i < list.size(); i++) {
                        if (i < (list.size() - 1)) {
                            EntityTrail speedMirage = list.get(i);
                            EntityTrail speedMirage2 = list.get(i + 1);
                            Vec3d start = speedMirage.getLightningPosVector(j);
                            Vec3d end = speedMirage2.getLightningPosVector(j);
                            float progress = 1F - (speedMirage.ticksExisted + partialRenderTicks) / 10F;
                            drawLine(start.add(add), end.add(add), 2, 1,
                                    speedMirage.lightningFactor[j] < 0.5F ? c : new Color(0.6F * 255, 0.6F * 255, 0.6F * 255), progress);
                        }
                    }
                }

                float a = 1F - (list.getLast().ticksExisted + partialRenderTicks) / 10F;
                Vec3d firstStart = (list.getLast().getPositionEyes(partialRenderTicks).subtract(list.getLast().getLightningPosVector(0)))
                        .add((player.getPositionEyes(partialRenderTicks).add(0.0D, -1.62F * (player.height / 1.8F), 0.0D)));
                //				drawInnerLight(firstStart.addVector(0, -entity.height/2, 0), list.get(list.size() - 1).getPositionVector(), entity.height, c, a);

                for (int i = 0; i < list.size(); i++) {
                    if (i < (list.size() - 1)) {
                        EntityTrail speedMirage = list.get(i);
                        EntityTrail speedMirage2 = list.get(i + 1);
                        float progress = 1F - (speedMirage.ticksExisted + partialRenderTicks) / 10F;
                        drawInnerLight(speedMirage.getPositionVector(), speedMirage2.getPositionVector(), speedMirage.height, c, progress);
                    }
                }
            }

            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }

        @Override
        public void renderSecondTrail(EntityPlayer player, TrailType trail, LinkedList<EntityTrailSecond> list, float partialRenderTicks) {
            if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && player == Minecraft.getMinecraft().player)
                return;

            Color c = getSecondTrailColor(player, trail);

            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 1);
            translateRendering(Minecraft.getMinecraft().player, player, partialRenderTicks);

            int amountOfLightnings = 7;
            float lightningSpace = player.height / amountOfLightnings;

            if (list.size() > 0) {
                for (int j = 0; j < amountOfLightnings; j++) {
                    // 10 Blitze ----------------------------------------------
                    Vec3d add = new Vec3d(0, j * lightningSpace, 0);

                    Vec3d firstStart = (list.getLast().getLightningPosVector(j)
                            .subtract(list.getLast().getPositionEyes(partialRenderTicks)))
                            .add((player.getPositionEyes(partialRenderTicks).add(0.0D, -1.62F * (player.height / 1.8F), 0.0D)));
                    Vec3d firstEnd = list.getLast().getLightningPosVector(j);
                    float a = 1F - (list.getLast().ticksExisted + partialRenderTicks) / 10F;
                    drawLine(firstStart.add(add.add(0, player.height, 0)), firstEnd.add(add), 2, 1,
                            list.getLast().lightningFactor[j] < 0.5F ? c : new Color(0.6F * 255, 0.6F * 255, 0.6F * 255), a);

                    for (int i = 0; i < list.size(); i++) {
                        if (i < (list.size() - 1)) {
                            EntityTrailSecond speedMirage = list.get(i);
                            EntityTrailSecond speedMirage2 = list.get(i + 1);
                            Vec3d start = speedMirage.getLightningPosVector(j);
                            Vec3d end = speedMirage2.getLightningPosVector(j);
                            float progress = 1F - (speedMirage.ticksExisted + partialRenderTicks) / 10F;
                            drawLine(start.add(add), end.add(add), 2, 1,
                                    speedMirage.lightningFactor[j] < 0.5F ? c : new Color(0.6F * 255, 0.6F * 255, 0.6F * 255), progress);
                        }
                    }
                }

                float a = 1F - (list.getLast().ticksExisted + partialRenderTicks) / 10F;
                Vec3d firstStart = (list.getLast().getPositionEyes(partialRenderTicks).subtract(list.getLast().getLightningPosVector(0)))
                        .add((player.getPositionEyes(partialRenderTicks).add(0.0D, -1.62F * (player.height / 1.8F), 0.0D)));
                //				drawInnerLight(firstStart.addVector(0, -entity.height/2, 0), list.get(list.size() - 1).getPositionVector(), entity.height, c, a);

                for (int i = 0; i < list.size(); i++) {
                    if (i < (list.size() - 1)) {
                        EntityTrailSecond speedMirage = list.get(i);
                        EntityTrailSecond speedMirage2 = list.get(i + 1);
                        float progress = 1F - (speedMirage.ticksExisted + partialRenderTicks) / 10F;
                        drawInnerLight(speedMirage.getPositionVector(), speedMirage2.getPositionVector(), speedMirage.height, c, progress);
                    }
                }
            }

            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }
    }

    public static class TrailRendererRandomLightnings extends TrailRendererLightnings {

        @Override
        public void renderTrail(EntityPlayer en, TrailType trail, LinkedList<EntityTrail> list, float partialRenderTicks) {
            if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && en == Minecraft.getMinecraft().player)
                return;

            Color c = getTrailColor(en, trail);

            Random rand = new Random();
            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 1);
            float lastBrightnessX = OpenGlHelper.lastBrightnessX;
            float lastBrightnessY = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
            EntityPlayer player = Minecraft.getMinecraft().player;
            translateRendering(player, en, partialRenderTicks);

            int radius = 6;
            float f = player.width / 2F;

            for (int i = 0; i < 20; i++) {
                if (i < list.size() - 1) {
                    EntityTrail mirage = list.get(i);

                    Vec3d start = en.getPositionVector().add(mirage.lightningFactor[0] * en.width - f, mirage.lightningFactor[0] * en.height,
                            mirage.lightningFactor[0] * en.width - f);
                    Vec3d end = new Vec3d(en.posX + f + rand.nextInt(radius) - radius / 2, en.posY + rand.nextFloat() * en.height,
                            en.posZ + f + rand.nextInt(radius) - radius / 2);
                    Vec3d middle = new Vec3d((start.x + end.x) / 2F + rand.nextFloat() - 0.5F,
                            (start.y + end.y) / 2F + rand.nextFloat() - 0.5F, (start.z + end.z) / 2F + rand.nextFloat() - 0.5F);
                    Vec3d quarter = new Vec3d((start.x + middle.x) / 2F + rand.nextFloat() - 0.5F,
                            (start.y + middle.y) / 2F + rand.nextFloat() - 0.5F, (start.z + middle.z) / 2F + rand.nextFloat() - 0.5F);
                    Vec3d thirdQuarter = new Vec3d((end.x + middle.x) / 2F + rand.nextFloat() - 0.5F,
                            (end.y + middle.y) / 2F + rand.nextFloat() - 0.5F, (end.z + middle.z) / 2F + rand.nextFloat() - 0.5F);

                    BlockPos posEnd = new BlockPos(end.x, end.y, end.z);
                    boolean isBlock = !player.world.isAirBlock(posEnd);
                    if (isBlock) {
                        drawLine(start, quarter, lineWidth, innerLineWidth, c, 1F);
                        drawLine(quarter, middle, lineWidth, innerLineWidth, c, 1F);
                        drawLine(middle, thirdQuarter, lineWidth, innerLineWidth, c, 1F);
                        drawLine(thirdQuarter, end, lineWidth, innerLineWidth, c, 1F);

                        drawLine(middle, new Vec3d(middle.x + (rand.nextFloat() - 0.5F), middle.y + (rand.nextFloat() - 0.5F),
                                middle.z + (rand.nextFloat() - 0.5F)), lineWidth, innerLineWidth, c, 1F);
                        drawLine(quarter, new Vec3d(quarter.x + (rand.nextFloat() - 0.5F), quarter.y + (rand.nextFloat() - 0.5F),
                                quarter.z + (rand.nextFloat() - 0.5F)), lineWidth, innerLineWidth, c, 1F);
                        drawLine(thirdQuarter, new Vec3d(thirdQuarter.x + (rand.nextFloat() - 0.5F), thirdQuarter.y + (rand.nextFloat() - 0.5F),
                                thirdQuarter.z + (rand.nextFloat() - 0.5F)), lineWidth, innerLineWidth, c, 1F);

                        player.world.spawnParticle(EnumParticleTypes.FLAME, end.x, end.y, end.z, 0F, 0.01F, 0F);
                        player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, end.x, end.y, end.z, 0F, 0.1F, 0F);
                    }
                }
            }

            GlStateManager.color(1, 1, 1, 1);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }

        @Override
        public void renderSecondTrail(EntityPlayer en, TrailType trail, LinkedList<EntityTrailSecond> list, float partialRenderTicks) {
            if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && en == Minecraft.getMinecraft().player)
                return;

            Color c = getSecondTrailColor(en, trail);

            Random rand = new Random();
            GlStateManager.pushMatrix();
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 1);
            float lastBrightnessX = OpenGlHelper.lastBrightnessX;
            float lastBrightnessY = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
            EntityPlayer player = Minecraft.getMinecraft().player;
            translateRendering(player, en, partialRenderTicks);

            int radius = 6;
            float f = player.width / 2F;

            for (int i = 0; i < 20; i++) {
                if (i < list.size() - 1) {
                    EntityTrailSecond mirage = list.get(i);

                    Vec3d start = en.getPositionVector().add(mirage.lightningFactor[0] * en.width - f, mirage.lightningFactor[0] * en.height,
                            mirage.lightningFactor[0] * en.width - f);
                    Vec3d end = new Vec3d(en.posX + f + rand.nextInt(radius) - radius / 2, en.posY + rand.nextFloat() * en.height,
                            en.posZ + f + rand.nextInt(radius) - radius / 2);
                    Vec3d middle = new Vec3d((start.x + end.x) / 2F + rand.nextFloat() - 0.5F,
                            (start.y + end.y) / 2F + rand.nextFloat() - 0.5F, (start.z + end.z) / 2F + rand.nextFloat() - 0.5F);
                    Vec3d quarter = new Vec3d((start.x + middle.x) / 2F + rand.nextFloat() - 0.5F,
                            (start.y + middle.y) / 2F + rand.nextFloat() - 0.5F, (start.z + middle.z) / 2F + rand.nextFloat() - 0.5F);
                    Vec3d thirdQuarter = new Vec3d((end.x + middle.x) / 2F + rand.nextFloat() - 0.5F,
                            (end.y + middle.y) / 2F + rand.nextFloat() - 0.5F, (end.z + middle.z) / 2F + rand.nextFloat() - 0.5F);

                    BlockPos posEnd = new BlockPos(end.x, end.y, end.z);
                    boolean isBlock = !player.world.isAirBlock(posEnd);
                    if (isBlock) {
                        drawLine(start, quarter, lineWidth, innerLineWidth, c, 1F);
                        drawLine(quarter, middle, lineWidth, innerLineWidth, c, 1F);
                        drawLine(middle, thirdQuarter, lineWidth, innerLineWidth, c, 1F);
                        drawLine(thirdQuarter, end, lineWidth, innerLineWidth, c, 1F);

                        drawLine(middle, new Vec3d(middle.x + (rand.nextFloat() - 0.5F), middle.y + (rand.nextFloat() - 0.5F),
                                middle.z + (rand.nextFloat() - 0.5F)), lineWidth, innerLineWidth, c, 1F);
                        drawLine(quarter, new Vec3d(quarter.x + (rand.nextFloat() - 0.5F), quarter.y + (rand.nextFloat() - 0.5F),
                                quarter.z + (rand.nextFloat() - 0.5F)), lineWidth, innerLineWidth, c, 1F);
                        drawLine(thirdQuarter, new Vec3d(thirdQuarter.x + (rand.nextFloat() - 0.5F), thirdQuarter.y + (rand.nextFloat() - 0.5F),
                                thirdQuarter.z + (rand.nextFloat() - 0.5F)), lineWidth, innerLineWidth, c, 1F);

                        player.world.spawnParticle(EnumParticleTypes.FLAME, end.x, end.y, end.z, 0F, 0.01F, 0F);
                        player.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, end.x, end.y, end.z, 0F, 0.1F, 0F);
                    }
                }
            }

            GlStateManager.color(1, 1, 1, 1);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.popMatrix();
        }
    }

    public static float median(double currentPos, double prevPos, float renderTick) {
        return (float) (prevPos + (currentPos - prevPos) * renderTick);
    }

    public static void translateRendering(EntityPlayer player, Entity entity, float renderTick) {
        double x = -median(entity.posX, entity.prevPosX, renderTick) - (median(player.posX, player.prevPosX, renderTick) - median(entity.posX, entity.prevPosX,
                renderTick));
        double y = -median(entity.posY, entity.prevPosY, renderTick) - (median(player.posY, player.prevPosY, renderTick) - median(entity.posY, entity.prevPosY,
                renderTick));
        double z = -median(entity.posZ, entity.prevPosZ, renderTick) - (median(player.posZ, player.prevPosZ, renderTick) - median(entity.posZ, entity.prevPosZ,
                renderTick));
        GlStateManager.translate((float) x, (float) y, (float) z);
    }

    private static float lastBrightnessX = OpenGlHelper.lastBrightnessX;
    private static float lastBrightnessY = OpenGlHelper.lastBrightnessY;

    public static void setLightmapTextureCoords(float x, float y) {
        lastBrightnessX = OpenGlHelper.lastBrightnessX;
        lastBrightnessY = OpenGlHelper.lastBrightnessY;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, x, y);
    }

    public static void restoreLightmapTextureCoords() {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
    }

    public static void drawLine(Vec3d start, Vec3d end, float lineWidth, float innerLineWidth, Color color, float alpha) {
        if (start == null || end == null)
            return;

        Tessellator tes = Tessellator.getInstance();
        BufferBuilder wr = tes.getBuffer();

        if (lineWidth > 0) {
            GlStateManager.color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, alpha);
            GlStateManager.glLineWidth(lineWidth);
            wr.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
            wr.pos(start.x, start.y, start.z).endVertex();
            wr.pos(end.x, end.y, end.z).endVertex();
            wr.pos(end.x, end.y, end.z).endVertex();
            tes.draw();
        }

        if (innerLineWidth > 0) {
            GlStateManager.color(color.getRed(), color.getGreen(), color.getBlue(), MathHelper.clamp(alpha - 0.2F, 0, 1));
            GlStateManager.glLineWidth(innerLineWidth);
            wr.begin(GL11.GL_LINE_STRIP, DefaultVertexFormats.POSITION);
            wr.pos(start.x, start.y, start.z).endVertex();
            wr.pos(end.x, end.y, end.z).endVertex();
            tes.draw();
        }
    }

}
