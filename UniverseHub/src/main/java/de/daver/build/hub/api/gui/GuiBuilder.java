package de.daver.build.hub.api.gui;

import de.daver.build.hub.api.gui.event.GuiCloseEvent;
import de.daver.build.hub.api.gui.event.GuiEvent;
import de.daver.build.hub.api.gui.event.GuiOpenEvent;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.util.ClickType;
import de.daver.build.hub.core.gui.GuiBuilderImpl;

import java.util.List;
import java.util.function.Supplier;

public interface GuiBuilder {

     //To set a Gui-Id to be referenced from other guis
     GuiBuilder id(String id);
     //To set a title
     GuiBuilder title(String title);
     //To apply a layout
     GuiBuilder layout(GuiLayout layout);
     //To set an item which cannot be replaced
     GuiBuilder staticItem(int position, Item item);
     //To execute an event when a user clicks on an item
     GuiBuilder addInteraction(int slot, GuiAction action, ClickType... clickTypes);
     //To switch to another Gui when clicking on an item
     GuiBuilder addGuiSwitch(int slot, String guiId, ClickType... clickTypes);
     //To switch a page, where new dynamic items are placed
     GuiBuilder addPageSwitch(int slot, boolean increase, ClickType... clickTypes);
     //To switch a page, where new dynamic items are placed
     GuiBuilder addPageSwitch(int slot);
     //To set a close button
     GuiBuilder closeOn(int slot);
     //To add an action which executes on a gui event
     GuiBuilder addEvent(GuiEvent event);
     //To add an action which executes on opening the gui
     GuiBuilder addOpenEvent(GuiOpenEvent event);
     //To add an action which executes on closing the gui
     GuiBuilder addCloseEvent(GuiCloseEvent event);
     //Slots where items can be pulled from the gui
     GuiBuilder accessible(int...slots);
     //A Function to resolve the dynamic items
     GuiBuilder dynamicItems(Supplier<List<Item>> itemSupplier);
     //Building the Gui Object
     Gui build();

     //Create a Builder by type
     static GuiBuilder create(GuiType type) {
         return new GuiBuilderImpl(type);
     }

     //Creates a ChestBuilder by how many chest rows
     static GuiBuilder create(int rows){
          return create(GuiType.getByRows(rows));
     }
}
