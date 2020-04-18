package com.zampa.goosegame.gamelogic;

enum SlotType {
    START, BASE, BRIDGE, GOOSE, FINAL
}

public class Board {

    private final int NUMBER_OF_SLOTS = 64;
    private Slot[] slots = new Slot[NUMBER_OF_SLOTS];

    public Board() {
        for (int slotNum=0; slotNum<NUMBER_OF_SLOTS; slotNum++) {
            switch (slotNum) {
                case 0:
                    slots[slotNum] = new Slot(slotNum, SlotType.START); break;
                case 6:
                    slots[slotNum] = new Slot(slotNum, SlotType.BRIDGE); break;
                case 5:
                case 9:
                case 14:
                case 18:
                case 23:
                case 27:
                    slots[slotNum] = new Slot(slotNum, SlotType.GOOSE); break;
                case NUMBER_OF_SLOTS - 1:
                    slots[slotNum] = new Slot(slotNum, SlotType.FINAL); break;
                default:
                    slots[slotNum] = new Slot(slotNum, SlotType.BASE);
            }
        }
    }

    /**
     * Returns the corresponding slot.
     * @param slotNum a number between 1 and 63
     * @return the corresponding slot
     * @throws IndexOutOfBoundsException if slotNum<1 or slotNum>63
     */
    public Slot getSlot(int slotNum) throws IndexOutOfBoundsException {
        return slots[slotNum];
    }

    /**
     * Return the slot
     * @param from
     * @param by
     * @return
     */
    public Slot advanceFromSlot(Slot from, int by) throws IndexOutOfBoundsException{
        return slots[from.getNumber() + by];
    }
}
