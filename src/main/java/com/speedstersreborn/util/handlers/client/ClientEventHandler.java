package com.speedstersreborn.util.handlers.client;

import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.speedstercap.PacketSetPhasing;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeed;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeedster;
import com.speedstersreborn.network.packets.speedstercap.PacketSetWallRunning;
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
    public static KeyBinding WALL_RUN;
    public static KeyBinding PHASING;

    public static void init() {
        SPEEDSTER = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds.speedster", Keyboard.KEY_X, SpeedsterHeroesReborn.NAME);
        ClientRegistry.registerKeyBinding(SPEEDSTER);
        UP = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds.up", Keyboard.KEY_N, SpeedsterHeroesReborn.NAME);
        ClientRegistry.registerKeyBinding(UP);
        WALL_RUN = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds.wall", Keyboard.KEY_O, SpeedsterHeroesReborn.NAME);
        ClientRegistry.registerKeyBinding(WALL_RUN);
        PHASING = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds.phasing", Keyboard.KEY_C, SpeedsterHeroesReborn.NAME);
        ClientRegistry.registerKeyBinding(PHASING);
    }

    @SubscribeEvent
    public static void onClientTick(InputUpdateEvent e) {
        if (SPEEDSTER.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeedster());
        }

        if (UP.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeed());
        }

        if(WALL_RUN.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new PacketSetWallRunning());
        }

        if(PHASING.isPressed()) {
            NetworkHandler.INSTANCE.sendToServer(new PacketSetPhasing());
        }
    }

    @Mod.EventBusSubscriber(modid = SpeedsterHeroesReborn.MODID, value = Side.CLIENT)
    public static class Renderer {
        }
}
