package com.speedstersreborn.util.handlers.client;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.revivalmodding.revivalcore.util.handlers.client.ClientEventHandler;
import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.api.SpeedAPI;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.speedstercap.PacketSetPhasing;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeed;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeedster;
import com.speedstersreborn.network.packets.speedstercap.PacketSetWallRunning;
import com.speedstersreborn.util.config.CFGOverlayPosition;
import com.speedstersreborn.util.config.SHRConfig;

import net.minecraft.client.Minecraft;
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

	static final ResourceLocation SPEED_INDICATOR = new ResourceLocation(SpeedsterHeroesReborn.MODID + ":textures/overlay/speed_indicators.png");
	
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
    	
    	IMetaCap cap = CapabilityMeta.get(player);
    	if(cap.hasMetaPowers() && MetaHelper.getMetaPowerName(cap.getMetaPower()) == MetaPowerStrings.SPEEDSTER) {
    		updateSpeed(SpeedAPI.isPlayerMoving(player) ? player.getAIMoveSpeed() : 0f);
    	}
    }
	
	@SubscribeEvent
	public static void onRenderPost(RenderGameOverlayEvent.Post e) {
		Minecraft mc = Minecraft.getMinecraft();
		if(e.getType() == ElementType.ALL) {
			IMetaCap cap = CapabilityMeta.get(mc.player);
			if(cap.hasMetaPowers() && MetaHelper.getMetaPowerName(cap.getMetaPower()) == MetaPowerStrings.SPEEDSTER) {
				doSpeedRender(mc, e.getResolution().getScaledHeight());
			}
		}
	}
	
	private static void doSpeedRender(Minecraft mc, int pixHeight) {
		CFGOverlayPosition pos = SHRConfig.speedstersHeroesReborn.speedIndicator;
		ImageHelper.drawImageWithUV(mc, SPEED_INDICATOR, pos.x, pixHeight-pos.y, 64, 10, 0, 0, 1, 0.3125D, false);
		float progress = speed / (SpeedAPI.MaxSpeedLevel*SHRConfig.speedstersHeroesReborn.speedIncreaseOverLevel);
		x = IndicatorAnimation.move(x, (int)(64*progress), 1.5f);
		ImageHelper.drawImageWithUV(mc, SPEED_INDICATOR, pos.x + x, pixHeight-pos.y, 8, 16, 0, 0.34375, 0.1875, 0.6875, true);
		
	}
	
	private static void updateSpeed(float newSpeed) {
		speed = newSpeed;
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
