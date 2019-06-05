package com.speedstersreborn.util.handlers.client;

import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.revivalmodding.revivalcore.util.handlers.client.Keybinds;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.speedstercap.PacketSetVelocity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;

public class SHKeybinds {

    public static void init() {
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
            EntityPlayerSP player = Minecraft.getMinecraft().player;
            IMetaCap metaCap = CapabilityMeta.get(player);
            ISpeedsterCap cap = CapabilitySpeedster.get(player);
            IAbilityCap abilityCap = IAbilityCap.Impl.get(player);

            if (metaCap.hasMetaPowers() && MetaHelper.getMetaPowerName(metaCap.getMetaPower()).equals(MetaPowerStrings.SPEEDSTER) && metaCap.isPowerEnabled()) {
            }else {
                // Velocity Compatibility
                if (Keybinds.ENABLE.isPressed() && cap.getVelocityTime() > 1) {
                    NetworkHandler.INSTANCE.sendToServer(new PacketSetVelocity());
                }
            }
        }
    }
}
