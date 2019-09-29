package com.speedstersreborn.common.suits;

import java.awt.Color;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.common.items.SHRItems;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Suit class for all flash suit variants
 */
public class FlashSuit extends AbstractSuit {

	public final EnumFlashSuitSeason suitSeason;

	public FlashSuit(EnumFlashSuitSeason suitSeason) {
		super("flash_suit", Color.RED);
		this.suitSeason = suitSeason;
	}
	
	@Override
	public ItemSuit getHelmet() {
		return suitSeason.getHelmet();
	}
	
	@Override
	public ItemSuit getChest() {
		return suitSeason.getChestplate();
	}
	
	@Override
	public ItemSuit getLeggings() {
		return suitSeason.getLeggings();
	}
	
	@Override
	public void handleEffects(EntityPlayer player) {
		
	}
	
	@Override
	public double getXPBonus() {
		return 0.01D;
	}

	public enum EnumFlashSuitSeason {
		S1,
		S2,
		S4,
		S5;

		public ItemFlashSuit getHelmet() {
			switch(this) {
				case S4: return SHRItems.s4_flash_helmet;
				default: return null;
			}
		}

		public ItemFlashSuit getChestplate() {
			switch(this) {
				case S4: return SHRItems.s4_flash_chestplate;
				default: return null;
			}
		}

		public ItemFlashSuit getLeggings() {
			switch(this) {
				case S4: return SHRItems.s4_flash_leggins;
				default: return null;
			}
		}
	}
}
