package de.daver.build.hub.api.scoreboard;

import de.daver.build.hub.api.util.Transmitter;

public interface Tablist extends Transmitter {

    //TODO

    void header(String...lines);
    void footer(String...lines);
}
