package com.speedstersreborn.util.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Name;

public class CFGOverlayPosition {
	
	@Config.Name("X")
	public int x;
	
	@Config.Name("Y")
	public int y;
	
	public CFGOverlayPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
