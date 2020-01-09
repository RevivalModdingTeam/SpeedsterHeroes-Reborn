package com.speedstersreborn.util.handlers.client;

import com.revivalmodding.revivalcore.core.capability.CoreCapabilityImpl;
import com.revivalmodding.revivalcore.core.capability.ICoreCapability;
import com.revivalmodding.revivalcore.core.capability.data.PlayerMetaPowerData;
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
            ICoreCapability coreCap = CoreCapabilityImpl.getInstance(player);
            PlayerMetaPowerData metaPowerData = coreCap.getMetaPowerData();
            ISpeedsterCap cap = CapabilitySpeedster.get(player);

            if (metaPowerData.hasMetaPowers() && MetaHelper.getMetaPowerName(metaPowerData.getMetaPower()).equals(MetaPowerStrings.SPEEDSTER) && metaPowerData.isPowerActivated()) {
            }else {
                // Velocity Compatibility
                if (Keybinds.ENABLE.isPressed() && cap.getVelocityTime() > 1) {
                    NetworkHandler.INSTANCE.sendToServer(new PacketSetVelocity());
                }
            }
        }
    }
}
