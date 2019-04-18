package com.speedstersreborn.util.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * Created by Josia50
 * on 10/04/2019.
 */

public class EnumHandler {

    public enum RingTypes implements IStringSerializable {

        ZOOM("zoom", 0),
        REVERSE("reverse_flash", 1),
        GOD_SPEED("god_speed", 2),
        KID_FLASH("kid_flash", 3);

        private int ID;
        private String name;

        RingTypes(String name, int ID) {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String toString() {
            return getName();
        }

        public int getID() {
            return ID;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
