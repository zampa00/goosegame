package com.zampa.goosegame.gamelogic;

public class Slot {

    int number;
    String name;
    SlotType type;

    public Slot(int number, SlotType type) {
        this.number = number;
        this.type = type;

        switch (type) {
            case START:
                this.name = "Start"; break;
            case BRIDGE:
                this.name = "The Bridge"; break;
            case GOOSE:
                this.name = number + ", The Goose"; break;
            default:
                this.name = Integer.toString(number);
        }
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public SlotType getType() {
        return type;
    }

    public boolean isSpecial() {
        return type == SlotType.BRIDGE || type == SlotType.GOOSE;
    }
}
