package com.speedstersreborn.common.ability;

import com.revivalmodding.revivalcore.core.common.events.RVRegistryEvent;
import com.revivalmodding.revivalcore.core.registry.Registries;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class SHRAbilities {

	public static final AbilitySpeed SPEED = new AbilitySpeed();
	public static final AbilityWallRunning WALL_RUNNING = new AbilityWallRunning();
	public static final AbilityPhase PHASE = new AbilityPhase();
	
	@SubscribeEvent
	public static void registerAbilities(RVRegistryEvent.AbilityRegistryEvent e) {
		e.register(SPEED);
		e.register(WALL_RUNNING);
		e.register(PHASE);
		System.out.println(Registries.ABILITIES);
	}
}
