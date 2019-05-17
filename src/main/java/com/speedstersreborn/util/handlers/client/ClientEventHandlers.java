package com.speedstersreborn.util.handlers.client;

import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.revivalmodding.revivalcore.util.handlers.client.ClientEventHandler;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.speedstercap.PacketSetPhasing;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeed;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeedster;
import com.speedstersreborn.network.packets.speedstercap.PacketSetWallRunning;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandlers {
    @SubscribeEvent
    public static void onClientTick(InputUpdateEvent e) {
        IMetaCap metaCap = CapabilityMeta.get(Minecraft.getMinecraft().player);

        if (metaCap.hasMetaPowers() && MetaHelper.getMetaPowerName(metaCap.getMetaPower()) == MetaPowerStrings.SPEEDSTER) {

            if (ClientEventHandler.ENABLE.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeedster());
            }

            if (ClientEventHandler.POWER1.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeed());
            }

            if (ClientEventHandler.POWER2.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new PacketSetWallRunning());
            }

            if (ClientEventHandler.POWER3.isPressed()) {
                NetworkHandler.INSTANCE.sendToServer(new PacketSetPhasing());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = SpeedsterHeroesReborn.MODID, value = Side.CLIENT)
    public static class Renderer {
    }
}
