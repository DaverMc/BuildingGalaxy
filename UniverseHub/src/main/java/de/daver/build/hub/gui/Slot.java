package de.daver.build.hub.gui;

import de.daver.build.hub.gui.action.GuiAction;
import de.daver.build.hub.item.Item;
import de.daver.build.hub.util.ClickType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Slot {

    private final Map<ClickType, List<GuiAction>> actions;
    private Item item;
    private boolean locked;

    public Slot(Item item, Map<ClickType, List<GuiAction>> actions) {
        this.actions = actions;
        this.item = item;
        this.locked = false;
    }

    public Slot() {
        this(null, new HashMap<>());
    }

    public Item getItem() {
        return this.item;
    }

    public boolean setItem(Item item) {
        if(this.locked) return false;
        this.item = item;
        return true;
    }

    public void lock() {
        this.locked = true;
    }

    public void addAction(ClickType type, GuiAction action) {
        List<GuiAction> actions = this.actions.get(type);
        if (actions == null) actions = new ArrayList<>();
        actions.add(action);
        this.actions.put(type, actions);
    }

    public List<GuiAction> getActions(ClickType type) {
        return actions.get(type);
    }

}
