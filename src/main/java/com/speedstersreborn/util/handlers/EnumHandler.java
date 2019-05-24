package com.speedstersreborn.util.handlers;

import com.revivalmodding.revivalcore.core.common.suits.AbstractSuit;
import com.revivalmodding.revivalcore.core.common.suits.SuitDebug;
import net.minecraft.util.IStringSerializable;

/**
 * Created by Josia50
 * on 10/04/2019.
 */

public class EnumHandler {

    public enum RingTypes implements IStringSerializable {

        ZOOM("zoom", new SuitDebug()),
        FLASH("flash", new SuitDebug()),
        REVERSE("reverse_flash", new SuitDebug()),
        GOD_SPEED("god_speed", new SuitDebug()),
        KID_FLASH("kid_flash", new SuitDebug());

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

        VelocityTypes(String name,int time, int addedspeed, int damage) {
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
