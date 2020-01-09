package com.speedstersreborn.common.ability;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.revivalmodding.revivalcore.core.abilities.IAbilityCap;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import com.speedstersreborn.util.handlers.EventHandlePower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

@Deprecated
public class AbilitySpeed extends AbilityBase {

	private static final ResourceLocation ICON = new ResourceLocation(SpeedsterHeroesReborn.MODID + ":textures/overlay/abilityspeed.png");

	public AbilitySpeed() {
		super("speed");
	}

	@Override
	public void update(EntityPlayer player) {
		ISpeedsterCap cap = CapabilitySpeedster.get(player);
		if(isActive()) {
			if(player.isSneaking() && cap.getSpeedLevel() > 0) {
				cap.setSpeedLevel(cap.getSpeedLevel()-1);
			} else if(!player.isSneaking() && cap.getSpeedLevel() < cap.getMaxspeedLevel()) {
				cap.setSpeedLevel(cap.getSpeedLevel()+1);
			}
			toggleAbility();
		}
		if(cap.getSpeedLevel() > 0) {
			if(EventHandlePower.isMoving(player)) {
				IAbilityCap ac = IAbilityCap.Impl.get(player);
				ac.addXP(0.01*cap.getSpeedLevel());
			}
		}
	}
	
	@Override
	public void onAbilityDeactivated(EntityPlayer player) {
		ISpeedsterCap cap = CapabilitySpeedster.get(player);
		if(cap.getSpeedLevel() > 0) {
			cap.setSpeedLevel(0);
			cap.sync();
		}
	}
	
	@Override
	public int getAbilityPrice() {
		return 0;
	}
	
	@Override
	public ResourceLocation getIcon() {
		return ICON;
	}
	
	@Override
	public String getFullName() {
		return "Ability Super Speed";
	}
}
