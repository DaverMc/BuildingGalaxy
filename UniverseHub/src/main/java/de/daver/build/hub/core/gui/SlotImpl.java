package de.daver.build.hub.core.gui;

import de.daver.build.hub.api.gui.GuiAction;
import de.daver.build.hub.api.gui.Slot;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.ClickType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlotImpl implements Slot {

    private final Map<ClickType, List<GuiAction>> actions;
    private Item item;
    private boolean locked; // static item cannot be replaceable
    private boolean accessible; //Spieler kann Item herausnehmen

    protected SlotImpl() {
        this.actions = new HashMap<>();
        this.item = null;
        this.locked = false;
        this.accessible = false;
    }

    public Item getItem() {
        return this.item;
    }

    public boolean setItem(Item item) {
        if(isLocked()) return false;
        this.item = item;
        return true;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    @Override
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public void addAction(ClickType type, GuiAction action) {
        List<GuiAction> actions = this.actions.get(type);
        if (actions == null) actions = new ArrayList<>();
        actions.add(action);
        this.actions.put(type, actions);
    }

    @Override
    public List<GuiAction> getActions(ClickType type) {
        return actions.get(type);
    }

    @Override
    public boolean isAccessible() {
        return this.accessible;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }
}
