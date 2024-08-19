package de.daver.build.hub.api.scoreboard;

import de.daver.build.hub.api.util.Transmitter;

public interface ScoreBoard extends Transmitter {

    void setLine(int index, String line, int score);

    void setTitle(String title);

}
