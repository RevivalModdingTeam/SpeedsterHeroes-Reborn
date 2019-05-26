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
		if(isActive()) {
			if(player.collidedHorizontally) {
				currentCooldown = 150;
			}
			
			if(!cap.isWallRunning()) {
				cap.setWallRunning(true);
			}
			if(hasCooldown()) {
				player.fallDistance = 0f;
			}
		} else if(!isActive() && cap.isWallRunning()) {
			cap.setWallRunning(false);
		}
		
		super.update(player);
	}
}
