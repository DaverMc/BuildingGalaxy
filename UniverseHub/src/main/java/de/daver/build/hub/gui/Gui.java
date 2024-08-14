package de.daver.build.hub.gui;

import de.daver.build.hub.item.Item;

import java.util.List;

public class Gui {

    private final Slot[] slots;

    public Gui(GuiType type) {
        this.slots = new Slot[type.slotCount()];
    }

    public boolean setItem(int slot, Item item) {
        return this.slots[slot].setItem(item);
    }

    public void fillItems(List<Item> items) {
        int index = 0;
        for (Slot slot : slots) {
            if(index >= items.size()) return;
            if (slot.setItem(items.get(index))) index++;
        }
    }

    public void setAccessable(boolean accessable, int...slots) {
        for(int slot : slots) {
            this.slots[slot].setAccessible(accessable);
        }
    }

    public Item getItem(int slot) {
        return this.slots[slot].getItem();
    }

    public Slot[] getSlots() {
        return this.slots;
    }

}
