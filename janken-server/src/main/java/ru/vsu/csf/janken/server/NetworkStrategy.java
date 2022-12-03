package ru.vsu.csf.janken.server;

import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.gameplay.PlayerStrategy;

public class NetworkStrategy implements PlayerStrategy {

    private Figure figure;

    public void setValue(String s) {
        figure = Figure.valueOf(s);
    }

    @Override
    public Figure getFigure() {
        return figure;
    }
}
