package ru.vsu.csf.janken.client;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.gameplay.PlayerStrategy;

public class PlayerUiStrategy implements PlayerStrategy {

    private final ObjectProperty<Figure> figure = new SimpleObjectProperty<>();

    @Override
    public Figure getFigure() {
        return figure.get();
    }

    public void setFigure(Figure figure) {
        this.figure.set(figure);
    }

    public ObjectProperty<Figure> figureProperty() {
        return figure;
    }
}