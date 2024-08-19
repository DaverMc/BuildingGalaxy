package de.daver.build.hub.api.scoreboard;

import de.daver.build.hub.util.User;
import de.daver.build.hub.api.util.Transmitter;

import java.awt.*;

public interface BossBar extends Transmitter {

    //TODO
    //Wenn eine Einladung zu einer Welt geschickt wird läuft diese in X-Sekunden ab.
    //Die Bossbar soll einen Countdown dafür darstellen


    void setText(String text);

    void setColor(Color color);

    //Enable or Disables the bar under the text
    void setVisible(boolean visible);

    //Sets max value
    void setMaxValue(int maxValue);

    //Sets current value
    void setValue(int value);
}
