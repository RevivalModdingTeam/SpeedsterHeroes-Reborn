package com.speedstersreborn.common.ability;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;

import net.minecraft.entity.player.EntityPlayer;

public class AbilityPhase extends AbilityBase {
	
	public AbilityPhase() {
		super("phase");
	}
	
	@Override
	public void update(EntityPlayer player) {
		ISpeedsterCap cap = CapabilitySpeedster.get(player);
		if(isActive() && !cap.isPhasing()) {
			cap.setPhasing(true);
		} else if(!isActive() && cap.isPhasing()) {
			cap.setPhasing(false);
		}
	}
}
