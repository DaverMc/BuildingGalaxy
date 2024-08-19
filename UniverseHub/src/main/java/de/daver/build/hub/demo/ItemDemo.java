package de.daver.build.hub.demo;

import de.daver.build.hub.api.item.ItemBuilder;
import de.daver.build.hub.api.util.EnchantmentBuilder;
import de.daver.build.hub.api.item.Item;
import de.daver.build.hub.api.item.Material;
import de.daver.build.hub.util.ClickType;
import de.daver.build.hub.util.User;

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
                .enchant(EnchantmentBuilder.create("sharpness").level(10).build())
                .build();

        Item i4 = ItemBuilder.create(Material.STONE)
                .displayName("Wand")
                .addLoreLine("This wand can send a message on right click")
                .addAction(ClickType.RIGHT, this::demoAction)
                .build();
    }

    public void demoAction(User holder, Item item) {
        holder.sendMessage("The wand has chosen you!");
    }

}
