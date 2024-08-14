package de.daver.build.universe.item;

import de.daver.build.universe.util.Player;

public interface ItemAction {

    void execute(Player holder, Item item);

}
