package ru.vsu.csf.janken.sdk.gameplay;

import ru.vsu.csf.janken.sdk.enums.Figure;

public class RandomPlayerStrategy implements PlayerStrategy{

    @Override
    public Figure getFigure() {
        return Figure.randomFigure();
    }
}