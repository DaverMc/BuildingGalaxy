package de.daver.build.universe.command;

import de.daver.build.universe.world.World;
import de.daver.build.universe.world.WorldMaster;

public class Argument {

    public Argument(String key) {

    }

    public Argument suggestion(Suggestion suggestion) {
        return this;
    }

    public Argument action(Action action) {
        return this;
    }

    public <T> Argument type(Type<T> type) {
        return this;
    }

    public interface Type<T> {

        Type<Integer> INT = Integer::parseInt;

        Argument.Type<World> WORLD = s -> WorldMaster.get().getWorld(s);

                T transformString(String s);

    }

}
