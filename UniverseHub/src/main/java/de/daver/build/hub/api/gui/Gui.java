package de.daver.build.hub.api.gui;

import de.daver.build.hub.core.gui.Slot;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.User;

import java.util.List;

public interface Gui {

    boolean setItem(int slot, Item item);

    void fillItems(List<Item> items);

    void setAccessible(boolean accessible, int...slots);

    void open(User user);

    Item getItem(int slot);

    Slot[] getSlots();

    GuiType getType();

}
