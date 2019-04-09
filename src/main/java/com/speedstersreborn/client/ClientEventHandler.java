package com.speedstersreborn.client;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.PacketSetSpeed;
import com.speedstersreborn.network.packets.PacketSetSpeedster;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import org.lwjgl.input.Keyboard;


@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientEventHandler {

    public static KeyBinding SPEEDSTER;
    public static KeyBinding UP;

    public static void init() {
        SPEEDSTER = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds.speedster", Keyboard.KEY_X, SpeedsterHeroesReborn.NAME);
        ClientRegistry.registerKeyBinding(SPEEDSTER);
        UP = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds.up", Keyboard.KEY_N, SpeedsterHeroesReborn.NAME);
        ClientRegistry.registerKeyBinding(UP);
    }

    @SubscribeEvent
    public static void onClientTick(InputUpdateEvent e) {
        if (SPEEDSTER.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeedster());
        }

        if (UP.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeed());
        }
    }
}