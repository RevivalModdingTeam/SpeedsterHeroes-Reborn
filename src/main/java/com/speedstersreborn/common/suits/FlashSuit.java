package com.speedstersreborn.common.suits;

import java.awt.Color;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.common.items.SHRItems;

import net.minecraft.entity.player.EntityPlayer;

public class FlashSuit extends AbstractSuit {
	
	public FlashSuit() {
		super("flash_suit", Color.RED);
	}
	
	@Override
	public ItemSuit getHelmet() {
		return SHRItems.flash_helmet;
	}
	
	@Override
	public ItemSuit getChest() {
		return SHRItems.flash_chestplate;
	}
	
	@Override
	public ItemSuit getLeggings() {
		return SHRItems.flash_leggings;
	}
	
	@Override
	public ItemSuit getBoots() {
		return SHRItems.flash_boots;
	}
	
	@Override
	public void handleEffects(EntityPlayer player) {
		
	}
	
	@Override
	public double getXPBonus() {
		return 0.01D;
	}
}
