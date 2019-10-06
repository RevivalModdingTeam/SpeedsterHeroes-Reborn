package com.speedstersreborn.util.handlers.client;

import com.revivalmodding.revivalcore.util.helper.ImageHelper;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.api.SpeedAPI;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.config.CFGOverlayPosition;
import com.speedstersreborn.util.config.CFGSpeedIndicatorUnit;
import com.speedstersreborn.util.config.SHRConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.text.DecimalFormat;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandlers {

    static final ResourceLocation SPEED_INDICATOR = new ResourceLocation(SpeedsterHeroesReborn.MODID + ":textures/overlay/speedbar.png");
    static final DecimalFormat FORMAT = new DecimalFormat("#####,##0.00");

    static double x;

    @SubscribeEvent
    public static void onRenderPost(RenderGameOverlayEvent.Post e) {
        Minecraft mc = Minecraft.getMinecraft();
        if (e.getType() == ElementType.ALL) {
            ISpeedsterCap cap = CapabilitySpeedster.get(mc.player);
            if (cap.isSpeedster() || cap.hasVelocity()) {
                doSpeedRender(mc, e.getResolution(), cap.getSpeedLevel() - 1);
            } else if (x != 0) {
                x = 0;
            }
        }
    }

    private static void doSpeedRender(Minecraft mc, ScaledResolution res, int level) {
        boolean render = true;
        switch (SHRConfig.speedstersHeroesReborn.displayMode) {
            case NEVER: render = false; break;
            case CREATIVE: render = mc.player.isCreative() && !mc.player.isSpectator(); break;
            case SURVIVAL: render = !mc.player.isCreative() && !mc.player.isSpectator(); break;
            case ALWAYS: render = true; break;
        }
        if (level < 0 || !render) return;
        CFGOverlayPosition pos = SHRConfig.speedstersHeroesReborn.speedIndicator;
        CFGSpeedIndicatorUnit speedUnit = SHRConfig.speedstersHeroesReborn.speedUnit;
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        int left = width / 2 - 64;
        int top = height - 62;
        ImageHelper.drawImageWithUV(mc, SPEED_INDICATOR, left + pos.x, top - pos.y, 128, 10, 0, 0, 1, 0.71428571428D, true);
        x = IndicatorAnimation.move(x, level * 6.4f, 0.5f);
        ImageHelper.drawImageWithUV(mc, SPEED_INDICATOR, left + pos.x + x, top + 8 - pos.y, 6, 3, 0, 0.73, 0.05, 1, true);
        mc.fontRenderer.drawStringWithShadow(FORMAT.format((SpeedAPI.getPlayerMovementSpeed(mc.player) * 20) * speedUnit.getMultiplier()) + " " + speedUnit.getName(), left + pos.x, top - pos.y - 9, 0xFFFFFF);

    }

    public static class IndicatorAnimation {

        public static double interpolate(double base) {
            return base * 60 / Minecraft.getDebugFPS();
        }

        public static double move(double current, float target, float modifier) {
            double d = Math.abs(current - target) < modifier ? target : current < target ? current + interpolate(modifier) : current - interpolate(modifier);
            return d;
        }
    }
}
