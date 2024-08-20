package de.daver.build.hub.api.gui;

import de.daver.build.hub.api.gui.event.GuiEvent;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.User;

import java.util.List;

public interface Gui {

    void setTitle(String title);

    boolean setItem(int slot, Item item);

    void setAccessible(int slot, boolean accessible);

    void setLocked(int slot, boolean locked);

    void fillItems(List<Item> items);

    Item getItem(int slot);

    GuiType getType();

    Slot[] getSlots();

    void open(User user);

    void update(User user);

    void close(User user);

    <E extends GuiEvent> E getEvent(Class<E> eventClass);

    String getTitle();
}
