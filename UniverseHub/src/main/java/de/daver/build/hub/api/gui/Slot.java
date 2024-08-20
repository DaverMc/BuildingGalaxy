package de.daver.build.hub.api.gui;

import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.ClickType;

import java.util.List;

public interface Slot {

    Item getItem();

    boolean setItem(Item item);

    //Items can be taken out
    void setAccessible(boolean accessible);

    //Items cannot be replaced by dynamic items
    void setLocked(boolean locked);

    void addAction(ClickType type, GuiAction action);

    List<GuiAction> getActions(ClickType type);

    boolean isAccessible();

    boolean isLocked();

}
