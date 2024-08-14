package de.daver.build.hub.item;

import de.daver.build.universe.util.Player;

public interface ItemAction {

    void execute(Player holder, Item item);

}
