package com.zampa.goosegame.gamelogic;

public class Player {

    String name;
    Slot currentSlot;
    Slot previousSlot;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCurrentSlot(Slot currentSlot) {
        this.previousSlot = this.currentSlot;
        this.currentSlot = currentSlot;
    }

    public Slot getCurrentSlot() {
        return currentSlot;
    }

    public Slot getPreviousSlot() {
        return previousSlot;
    }
}
