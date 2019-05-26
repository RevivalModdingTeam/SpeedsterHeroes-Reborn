package com.speedstersreborn.util.handlers.client;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.revivalmodding.revivalcore.meta.capability.CapabilityMeta;
import com.revivalmodding.revivalcore.meta.capability.IMetaCap;
import com.revivalmodding.revivalcore.meta.util.MetaHelper;
import com.revivalmodding.revivalcore.meta.util.MetaPowerStrings;
import com.revivalmodding.revivalcore.network.NetworkManager;
import com.revivalmodding.revivalcore.network.packets.PacketSetPower;
import com.revivalmodding.revivalcore.util.handlers.client.Keybinds;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.ability.AbilityPhase;
import com.speedstersreborn.common.ability.AbilitySpeed;
import com.speedstersreborn.common.ability.AbilityWallRunning;
import com.speedstersreborn.common.ability.SHRAbilities;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.network.NetworkHandler;
import com.speedstersreborn.network.packets.speedstercap.PacketSetPhasing;
import com.speedstersreborn.network.packets.speedstercap.PacketSetSpeed;
import com.speedstersreborn.network.packets.speedstercap.PacketSetWallRunning;
import com.speedstersreborn.network.packets.speedstercap.PacketToggleAbility;

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
            
            if (metaCap.hasMetaPowers() && MetaHelper.getMetaPowerName(metaCap.getMetaPower()).equals(MetaPowerStrings.SPEEDSTER) && metaCap.isPowerEnabled() || cap.hasVelocity()) {

            	if(abilityCap.getAbilities(player).length < 3) {
            		abilityCap.setAbilities(new AbilityBase[] {SHRAbilities.SPEED, SHRAbilities.WALL_RUNNING, SHRAbilities.PHASE});
            	}
                // Velocity Compatibility
                if(Keybinds.ENABLE.isPressed() && cap.hasVelocity()) {
                    NetworkManager.INSTANCE.sendToServer(new PacketSetPower());
                }

                if (Keybinds.POWER1.isPressed()) {
                    NetworkHandler.INSTANCE.sendToServer(new PacketToggleAbility(0));
                    if(abilityCap.getAbilities(player)[0] instanceof AbilitySpeed) {
                    	abilityCap.getAbilities(player)[0].toggleAbility();
                    }
                }

                if (Keybinds.POWER2.isPressed()) {
                    NetworkHandler.INSTANCE.sendToServer(new PacketToggleAbility(1));
                }

                if (Keybinds.POWER3.isPressed()) {
                	NetworkHandler.INSTANCE.sendToServer(new PacketToggleAbility(2));
                }
            }
        }
    }
}
