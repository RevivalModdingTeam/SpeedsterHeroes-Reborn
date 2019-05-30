package com.speedstersreborn.common.ability;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class AbilityPhase extends AbilityBase {
	
	// TODO
	private static final ResourceLocation ICON = new ResourceLocation(SpeedsterHeroesReborn.MODID);
	
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
	
	@Override
	public int getAbilityPrice() {
		return 2;
	}
	
	@Override
	public String getFullName() {
		return "Ability Phase";
	}
	
	@Override
	public ResourceLocation getIcon() {
		return ICON;
	}
}
