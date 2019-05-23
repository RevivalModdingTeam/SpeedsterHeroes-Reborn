package com.speedstersreborn.util.handlers.client;

import org.lwjgl.input.Keyboard;

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
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

public class SHKeybinds {
	
    public static KeyBinding ENABLE;
    public static KeyBinding POWER1;
    public static KeyBinding POWER2;
    public static KeyBinding POWER3;
    public static KeyBinding ABILITY_GUI;

    public static void init() {
    	ENABLE = init("enable", Keyboard.KEY_X);
    	POWER1 = init("power1", Keyboard.KEY_N);
    	POWER2 = init("power2", Keyboard.KEY_O);
    	POWER3 = init("power3", Keyboard.KEY_C);
    	ABILITY_GUI = init("abilitygui", Keyboard.KEY_Y);
    }
    
    private static KeyBinding init(String langKey, int key) {
    	KeyBinding keyBinding = new KeyBinding(SpeedsterHeroesReborn.MODID + ".keybinds." + langKey, key, SpeedsterHeroesReborn.NAME);
    	ClientRegistry.registerKeyBinding(keyBinding);
    	return keyBinding;
    }
    
    @EventBusSubscriber(Side.CLIENT)
    public static class Handler {
    	
    	@SubscribeEvent
    	public static void keyPressed(InputEvent.KeyInputEvent e) {
            IMetaCap metaCap = CapabilityMeta.get(Minecraft.getMinecraft().player);
            
            if (metaCap.hasMetaPowers() && MetaHelper.getMetaPowerName(metaCap.getMetaPower()) == MetaPowerStrings.SPEEDSTER) {

                if (SHKeybinds.ENABLE.isPressed()) {
                    NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeedster());
                }

                else if (SHKeybinds.POWER1.isPressed()) {
                    NetworkHandler.INSTANCE.sendToServer(new PacketSetSpeed());
                }

                else if (SHKeybinds.POWER2.isPressed()) {
                    NetworkHandler.INSTANCE.sendToServer(new PacketSetWallRunning());
                }

                else if (SHKeybinds.POWER3.isPressed()) {
                    NetworkHandler.INSTANCE.sendToServer(new PacketSetPhasing());
                }
            }
    	}
    }
}
