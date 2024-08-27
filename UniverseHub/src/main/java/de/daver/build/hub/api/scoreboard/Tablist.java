package de.daver.build.hub.api.scoreboard;

import de.daver.build.hub.api.util.Transmitter;

public interface Tablist extends Transmitter {

    //TODO

    //Entries Prefix und Suffix und evt. noch den Entry

    void header(String...lines);
    void footer(String...lines);
}
