package com.speedstersreborn.common.ability;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.entity.player.EntityPlayer;

public class AbilitySpeed extends AbilityBase {

	private ISpeedsterCap cap;
	private EntityPlayer player;

	public AbilitySpeed() {
		super("speed");
	}

	@Override
	public void update(EntityPlayer player) {
		this.player = player;
		cap = CapabilitySpeedster.get(player);
	}

	@Override
	public void toggleAbility() {
		if(player == null || cap == null) {
			return;
		}

		int level = cap.getSpeedLevel();
		if(player.isSneaking() && level > 0) {
			level -= 1;
		}
		else if(!player.isSneaking()) {
			if(level < cap.getMaxspeedLevel()) {
				level += 1;
			}
		}
		cap.setSpeedLevel(level);
	}
}
