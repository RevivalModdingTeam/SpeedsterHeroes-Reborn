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

    public enum PowerTypes implements IStringSerializable {
        ;

        private int ID;
        private String name;

        PowerTypes(String name, int ID) {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }
}
