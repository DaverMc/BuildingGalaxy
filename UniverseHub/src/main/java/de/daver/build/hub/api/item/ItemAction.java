package de.daver.build.hub.api.item;

import de.daver.build.hub.api.util.User;

public interface ItemAction {

    void execute(User holder, Item item);

}
