package de.daver.build.hub.core.gui;

import de.daver.build.hub.api.gui.GuiType;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.util.User;

import java.util.List;
import java.util.function.Supplier;

public class GuiImpl {

    private final Slot[] slots;
    private final GuiType type;
    private final Supplier<List<Item>> dynamicItemSupplier;

    public GuiImpl(GuiType type, Supplier<List<Item>> dynamicItemSupplier) {
        this.type = type;
        this.slots = new Slot[type.getSlotCount()];
        this.dynamicItemSupplier = dynamicItemSupplier;
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

    public void open(User user) {

    }

    public Item getItem(int slot) {
        return this.slots[slot].getItem();
    }

    public Slot[] getSlots() {
        return this.slots;
    }

}
