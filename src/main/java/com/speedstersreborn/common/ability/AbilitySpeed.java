package com.speedstersreborn.common.ability;

import com.revivalmodding.revivalcore.core.abilities.AbilityBase;
import com.speedstersreborn.SpeedsterHeroesReborn;
import com.speedstersreborn.common.capabilities.CapabilitySpeedster;
import com.speedstersreborn.common.capabilities.ISpeedsterCap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class AbilitySpeed extends AbilityBase {

	// TODO
	private static final ResourceLocation ICON = new ResourceLocation(SpeedsterHeroesReborn.MODID);
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

	// TODO: fix
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
	
	@Override
	public int getAbilityPrice() {
		return 1;
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
