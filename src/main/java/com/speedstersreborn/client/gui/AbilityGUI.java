package com.speedstersreborn.client.gui;

import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;

/** WIP **/
public class AbilityGUI extends GuiScreen {
	
	int scrollAmount;
	
	public AbilityGUI(EntityPlayer player) {
		
	}
	
	@Override
	public void initGui() {
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public void drawIcons() {
		
	}
	
	private void drawAbilities() {
		
	}
	
	private void drawLevelStuff(ISpeedsterCap cap) {
		double progress = (cap.getXP() - cap.getLevel()*100) / 100D;
	}
}
