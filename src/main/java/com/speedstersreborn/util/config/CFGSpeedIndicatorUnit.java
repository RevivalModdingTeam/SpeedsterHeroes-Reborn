package com.speedstersreborn.util.config;

import net.minecraft.util.IStringSerializable;

public enum CFGSpeedIndicatorUnit implements IStringSerializable
{
	BLOCKS_PER_SECOND("Blocks/s", 1),
	KILOMETRES_PER_HOUR("km/h", 3.6),
	METRES_PER_SECOND("m/s", 1);
	
	private final String name;
	private final double multiplier;
	
	CFGSpeedIndicatorUnit(String name, double multiplier) {
		this.name = name;
		this.multiplier = multiplier;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public double getMultiplier() {
		return multiplier;
	}
}
