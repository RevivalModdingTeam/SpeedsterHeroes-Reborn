package com.speedstersreborn.common.ability;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;

import net.minecraft.entity.player.EntityPlayer;

public class AbilityWallRunning extends AbilityBase {

	public AbilityWallRunning() {
		super("wallrunning");
	}
	
	@Override
	public void update(EntityPlayer player) {
		ISpeedsterCap cap = CapabilitySpeedster.get(player);
		if(isActive() && !cap.isWallRunning()) {
			cap.setWallRunning(true);
		} else if(!isActive() && cap.isWallRunning()) {
			cap.setWallRunning(false);
		}
	}
}
