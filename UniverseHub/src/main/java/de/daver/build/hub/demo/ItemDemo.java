package de.daver.build.hub.demo;

import de.daver.build.hub.item.ItemBuilder;
import de.daver.build.hub.item.Enchantment;
import de.daver.build.hub.item.Item;
import de.daver.build.hub.item.Material;
import de.daver.build.hub.util.ClickType;
import de.daver.build.hub.util.Player;

public class ItemDemo {

    //Flags fehlen
    //Lore per LanguageKey
    //Durability hinzufügen
    public void demo() {
        Item i1 = ItemBuilder.create(Material.STONE)
                .displayName("Temporary Unavailable")
                .addLoreLine("This is the first Line of the Lore!")
                .addLoreLine("Do you wanna add some more?")
                .addLoreLine("No problem here's another one.")
                .build();

        Item i2 = ItemBuilder.create(Material.STONE)
                .displayName("Selected!")
                .addLoreLine("This one glows as if it is selected.")
                .glow()
                .build();

        Item i3 = ItemBuilder.create(Material.STONE)
                .displayName("Spiky Stone")
                .addLoreLine("This one is enchanted")
                .enchant(new Enchantment("sharpness", 10))
                .build();

        Item i4 = ItemBuilder.create(Material.STONE)
                .displayName("Wand")
                .addLoreLine("This wand can send a message on right click")
                .addAction(ClickType.RIGHT, this::demoAction)
                .build();
    }

    public void demoAction(Player holder, Item item) {
        holder.sendMessage("The wand has chosen you!");
    }

}
