package com.zampa.goosegame.gamelogic;

enum SlotType {
    START, BASE, BRIDGE, GOOSE, FINAL
}

public class Board {

    private final int NUMBER_OF_SLOTS = 63;
    private Slot[] slots = new Slot[NUMBER_OF_SLOTS];

    public Board() {
        for (int slotNum=0; slotNum<NUMBER_OF_SLOTS; slotNum++) {
            switch (slotNum) {
                case 0:
                    slots[slotNum] = new Slot(slotNum+1, SlotType.START); break;
                case 5:
                    slots[slotNum] = new Slot(slotNum+1, SlotType.BRIDGE); break;
                case 4:
                case 8:
                case 13:
                case 17:
                case 22:
                case 26:
                    slots[slotNum] = new Slot(slotNum+1, SlotType.GOOSE); break;
                case NUMBER_OF_SLOTS - 1:
                    slots[slotNum] = new Slot(slotNum+1, SlotType.FINAL); break;
                default:
                    slots[slotNum] = new Slot(slotNum+1, SlotType.BASE);
            }
        }
    }

    /**
     * Returns the corresponding slot
     * @param slotNum a number between 1 and 63
     * @return the corresponding slot
     * @throws IndexOutOfBoundsException if slotNum<1 or slotNum>63
     */
    public Slot getSlot(int slotNum) throws IndexOutOfBoundsException {
        return slots[slotNum-1];
    }
}
