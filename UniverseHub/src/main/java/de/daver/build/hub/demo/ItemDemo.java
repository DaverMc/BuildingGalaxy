package de.daver.build.hub.demo;

import de.daver.build.hub.api.item.*;
import de.daver.build.hub.api.util.ClickType;
import de.daver.build.hub.api.util.User;

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
                .enchant(EnchantmentBuilder.create(EnchantmentType.SHARPNESS)
                        .level(10)
                        .build())
                .build();

        Item i4 = ItemBuilder.create(Material.STONE)
                .displayName("Wand")
                .addLoreLine("This wand can send a message on right click")
                .addAction(ClickType.RIGHT, this::demoAction)
                .build();
    }

    public void demoAction(User holder, Item item) {
        holder.send("The wand has chosen you!");
    }

}
