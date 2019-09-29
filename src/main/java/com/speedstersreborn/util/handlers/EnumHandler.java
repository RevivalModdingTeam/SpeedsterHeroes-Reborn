package com.speedstersreborn.util.handlers;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.speedstersreborn.common.suits.SuitFlash;
import net.minecraft.util.IStringSerializable;

/**
 * Created by Josia50
 * on 10/04/2019.
 */

public class EnumHandler {

    public enum RingTypes implements IStringSerializable {

        // TODO proper suits
        ZOOM("zoom", new SuitFlash(SuitFlash.EnumFlashSuitSeason.S1)),
        FLASH("flash", new SuitFlash(SuitFlash.EnumFlashSuitSeason.S2)),
        REVERSE("reverse_flash", new SuitFlash(SuitFlash.EnumFlashSuitSeason.S4)),
        GOD_SPEED("god_speed", new SuitFlash(SuitFlash.EnumFlashSuitSeason.S4)),
        KID_FLASH("kid_flash", new SuitFlash(SuitFlash.EnumFlashSuitSeason.S5));

        private String name;
        private AbstractSuit bsuit;

        RingTypes(String name, AbstractSuit suit) {
            this.name = name;
            this.bsuit = suit;
        }

        @Override
        public String toString() {
            return getName();
        }

        public AbstractSuit getBsuit() {
            return bsuit;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }


    public enum VelocityTypes implements IStringSerializable {
        VELOCITY_NINE("v9", 60, 5, 1);

        private String names;
        private int timeleft;
        private int addspeedlevels;
        private int damages;

        VelocityTypes(String name, int time, int addedspeed, int damage) {
            this.names = name;
            this.timeleft = time;
            this.addspeedlevels = addedspeed;
            this.damages = damage;
        }

        public int getDamages() {
            return damages;
        }

        public int getMaxAddedSpeedLevels() {
            return addspeedlevels;
        }

        public int getTimeleft() {
            return timeleft;
        }

        @Override
        public String getName() {
            return this.names;
        }
    }
}
