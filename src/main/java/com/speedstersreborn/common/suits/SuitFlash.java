package com.speedstersreborn.common.suits;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.ItemSuit;
import com.speedstersreborn.common.items.SHRItemRegistry;
import net.minecraft.entity.player.EntityPlayer;

import java.awt.*;

/**
 * Suit class for all flash suit variants
 */
public class SuitFlash extends AbstractSuit {

	public final EnumFlashSuitSeason suitSeason;

	public SuitFlash(EnumFlashSuitSeason suitSeason) {
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
	public float getXPBonus() {
		return 0.01F;
	}

	public enum EnumFlashSuitSeason {
		S1,
		S2,
		S4,
		S5;

		public ItemSuit getHelmet() {
			switch(this) {
				case S1: return SHRItemRegistry.SHRItems.S1_FLASH_HEAD;
				case S2: return SHRItemRegistry.SHRItems.S2_FLASH_HEAD;
				case S4: return SHRItemRegistry.SHRItems.S4_FLASH_HEAD;
				case S5: return SHRItemRegistry.SHRItems.S5_FLASH_HEAD;
				default: return null;
			}
		}

		public ItemSuit getChestplate() {
			switch(this) {
				case S1: return SHRItemRegistry.SHRItems.S1_FLASH_CHEST;
				case S2: return SHRItemRegistry.SHRItems.S2_FLASH_CHEST;
				case S4: return SHRItemRegistry.SHRItems.S4_FLASH_CHEST;
				case S5: return SHRItemRegistry.SHRItems.S5_FLASH_CHEST;
				default: return null;
			}
		}

		public ItemSuit getLeggings() {
			switch(this) {
				case S1: return SHRItemRegistry.SHRItems.S1_FLASH_LEGS;
				case S2: return SHRItemRegistry.SHRItems.S2_FLASH_LEGS;
				case S4: return SHRItemRegistry.SHRItems.S4_FLASH_LEGS;
				case S5: return SHRItemRegistry.SHRItems.S5_FLASH_LEGS;
				default: return null;
			}
		}
	}
}
