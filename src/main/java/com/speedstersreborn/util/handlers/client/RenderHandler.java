package com.speedstersreborn.util.handlers.client;

import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RenderHandler {

    @SubscribeEvent
    public static void onPlayerRenderPre(RenderPlayerEvent.Pre e) {
        GlStateManager.pushMatrix();
        ISpeedsterCap cap = CapabilitySpeedster.get(e.getEntityPlayer());
        IAbilityCap capA = IAbilityCap.Impl.get(e.getEntityPlayer());
        if (cap != null) {
            EntityPlayer player = e.getEntityPlayer();
            Vec3d vec0 = new Vec3d(player.posX, player.posY + 1, player.posZ);
            Vec3d vec1 = player.getLookVec();
            Vec3d vec2 = vec0.add(vec1);
            RayTraceResult rayTraceResult = player.world.rayTraceBlocks(vec0, vec2, false, true, false);
            boolean posChanged = player.prevPosY != player.posY;
            boolean isActuallyWallrunning = rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK && posChanged;
            if(!cap.isWallRunning() || !isActuallyWallrunning) {
                return;
            }

            /** TODO: needs to be injected into the render() method */
            /*RenderPlayer playerRenderer = e.getRenderer();
            float pre = player.prevLimbSwingAmount + (player.limbSwingAmount - player.prevLimbSwingAmount) * e.getPartialRenderTick();
            float current = player.limbSwing - player.limbSwingAmount * (1.0F - e.getPartialRenderTick());
            float age = (float)player.ticksExisted + e.getPartialRenderTick();
            float yaw = interpolateRotation(player.prevRenderYawOffset, player.renderYawOffset, e.getPartialRenderTick());
            float yawHead = interpolateRotation(player.prevRotationYawHead, player.rotationYawHead, e.getPartialRenderTick());
            float bodyYawRotation = yawHead - yaw;
            float pitchIntepolated = interpolate(player.prevRotationPitch, player.rotationPitch, e.getPartialRenderTick());
            float scale = playerRenderer.prepareScale((AbstractClientPlayer)player, e.getPartialRenderTick());
            playerRenderer.getMainModel().setRotationAngles(current, pre, age, bodyYawRotation, pitchIntepolated, scale, player);*/

            EnumFacing facing = player.getHorizontalFacing();
            switch(facing) {
                case NORTH: {
                    GlStateManager.rotate(90f, 1f, 0f, 0f);
                    break;
                }
                case SOUTH: {
                    GlStateManager.rotate(270f, 1f, 0f, 0f);
                    break;
                }
                case WEST: {
                    GlStateManager.rotate(270f, 0f, 0f, 1f);
                    break;
                }
                case EAST: {
                    GlStateManager.rotate(90f, 0f, 0f, 1f);
                    break;
                }
                default:
                    break;
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRenderPost(RenderPlayerEvent.Post e) {
        GlStateManager.popMatrix();
    }

    private static float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
        // Based on Vanilla code
        float f;
        for (f = yawOffset - prevYawOffset; f < -180.0F; f += 360.0F) {}

        while (f >= 180.0F) {
            f -= 360.0F;
        }
        return prevYawOffset + partialTicks * f;
    }

    private static float interpolate(float prev, float current, float partial) {
        return prev + (current - prev) * partial;
    }
}
