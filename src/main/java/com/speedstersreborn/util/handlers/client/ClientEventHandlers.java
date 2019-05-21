package com.speedstersreborn.util.handlers.client;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.revivalmodding.revivalcore.util.handlers.client.ClientEventHandler;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.api.SpeedAPI;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.speedstercap.PacketSetPhasing;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeed;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeedster;
import com.speedstersreborn.network.packets.speedstercap.PacketSetWallRunning;
import com.speedstersreborn.util.config.CFGOverlayPosition;
import com.speedstersreborn.util.config.SHRConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandlers {

	static final ResourceLocation SPEED_INDICATOR = new ResourceLocation(SpeedsterHeroesReborn.MODID + ":textures/overlay/speedbar.png");
	
	static float speed;
	static int x;
	
	@SubscribeEvent
	public static void keyPressed(InputEvent.KeyInputEvent e) {
        IMetaCap metaCap = CapabilityMeta.get(Minecraft.getMinecraft().player);
        
        if (metaCap.hasMetaPowers() && MetaHelper.getMetaPowerName(metaCap.getMetaPower()) == MetaPowerStrings.SPEEDSTER) {

            if (ClientEventHandler.ENABLE.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeedster());
            }

            else if (ClientEventHandler.POWER1.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeed());
            }

            else if (ClientEventHandler.POWER2.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new PacketSetWallRunning());
            }

            else if (ClientEventHandler.POWER3.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new PacketSetPhasing());
            }
        }
	}
	
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
    	EntityPlayer player = Minecraft.getMinecraft().player;
    	if(player == null) {
    		return;
    	}
    	ISpeedsterCap cap = CapabilitySpeedster.get(player);
    	if(cap.isSpeedster()) {
    		updateSpeed(SpeedAPI.isPlayerMoving(player) ? player.getAIMoveSpeed() : 0f);
    	}
    }
	
	@SubscribeEvent
	public static void onRenderPost(RenderGameOverlayEvent.Post e) {
		Minecraft mc = Minecraft.getMinecraft();
		if(e.getType() == ElementType.ALL) {
			ISpeedsterCap cap = CapabilitySpeedster.get(mc.player);
			if(cap.isSpeedster()) {
				doSpeedRender(mc, e.getResolution(), cap.getSpeedLevel() - 1);
			}
		}
	}
	
	private static void doSpeedRender(Minecraft mc, ScaledResolution res, int level) {
		CFGOverlayPosition pos = SHRConfig.speedstersHeroesReborn.speedIndicator;
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();
		int left = width / 2 - 63;
		int top = height - 62;
		ImageHelper.drawImageWithUV(mc, SPEED_INDICATOR, left, top-pos.y, 128, 10, 0, 0, 1, 0.716D, false);
		
		if(level < 0)
			return;
		x = IndicatorAnimation.move(x, (int)(level*6.4), 0.5f);
		drawImageWithUV(mc, SPEED_INDICATOR, left-1 + pos.x + x, top+8-pos.y, 8, 4, 0, 0.733, 0.05197505197, 0.9333, true);
		
	}
	
	private static void updateSpeed(float newSpeed) {
		speed = newSpeed;
	}
	
	// TODO change the startX,Y to double inside ImageHelper
    public static void drawImageWithUV(Minecraft minecraft, ResourceLocation imageLocation, double startX, double startY, double width, double height, double startU, double startV, double u, double v, boolean transparent)
    {
        minecraft.getTextureManager().bindTexture(imageLocation);
        GlStateManager.color(1f, 1f, 1f);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        if(u > 1) u = 1;
        if(v > 1) v = 1;

        prepareShape(buffer, startX, startY, width, height, startU, startV, u, v);

        if(transparent)
        {
            GlStateManager.enableAlpha();
            GlStateManager.enableBlend();
            tessellator.draw();
            GlStateManager.disableBlend();
            GlStateManager.disableAlpha();
        }

        else tessellator.draw();
    }
    
    private static void prepareShape(BufferBuilder buffer, double startX, double startY, double width, double height, double startU, double startV, double endU, double endV)
    {
    	buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(startX, startY + height, 0).tex(startU, endV).endVertex();
        buffer.pos(startX + width, startY + height, 0).tex(endU, endV).endVertex();
        buffer.pos(startX + width, startY, 0).tex(endU, startV).endVertex();
        buffer.pos(startX, startY, 0).tex(startU, startV).endVertex();
    }
	
	public static class IndicatorAnimation {
		
		public static int interpolate(float base) {
			return (int)(base * 60 / Minecraft.getDebugFPS());
		}
		
		public static int move(int current, int target, float modifier) {
			float f = Math.abs(current - target) < modifier ? target : current < target ? current + interpolate(modifier) : current - interpolate(modifier);
			return (int)f;
		}
	}
}
