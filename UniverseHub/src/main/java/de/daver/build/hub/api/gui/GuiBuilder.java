package de.daver.build.hub.api.gui;

import de.daver.build.hub.api.gui.event.GuiCloseEvent;
import de.daver.build.hub.api.gui.event.GuiEvent;
import de.daver.build.hub.api.gui.event.GuiOpenEvent;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.util.ClickType;

import java.util.List;
import java.util.function.Supplier;

public interface GuiBuilder {

     GuiBuilder addInteraction(int slot, GuiAction action, ClickType... clickTypes);
     GuiBuilder addGuiSwitch(int slot, String guiId, ClickType... clickTypes);
     GuiBuilder addPageSwitch(int slot, boolean increase, ClickType... clickTypes);
     GuiBuilder addPageSwitch(int slot);
     GuiBuilder closeOn(int slot);
     GuiBuilder addEvent(GuiEvent event);
     GuiBuilder addOpenEvent(GuiOpenEvent event);
     GuiBuilder addCloseEvent(GuiCloseEvent event);
     GuiBuilder accessible(int...slots);
     GuiBuilder dynamicItems(Supplier<List<Item>> itemSupplier);
     Gui build();

     static GuiBuilder create(GuiType type) {
         return null;
     }
}
