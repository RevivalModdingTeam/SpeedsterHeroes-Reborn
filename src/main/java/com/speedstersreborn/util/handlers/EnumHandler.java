package com.speedstersreborn.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {

    public static enum RingTypes implements IStringSerializable {

        ZOOM("zoom", 0),
        REVERSE("reverse_flash", 1);

        private int ID;
        private String name;

        private RingTypes(String name, int ID) {
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
