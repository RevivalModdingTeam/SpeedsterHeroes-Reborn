package com.speedstersreborn.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {

    public enum RingTypes implements IStringSerializable {

        ZOOM("zoom", 0, 0),
        REVERSE("reverse_flash", 1,1);

        private int ID;
        private String name;
        private int suit;

         RingTypes(String name, int ID, int suit) {
            this.ID = ID;
            this.name = name;
            this.suit = suit;
        }

        @Override
        public String toString() {
            return getName();
        }

        public int getID() {
            return ID;
        }

        public int getSuit() {
            return suit;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }
}
