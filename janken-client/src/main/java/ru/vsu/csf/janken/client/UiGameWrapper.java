package ru.vsu.csf.janken.client;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.vsu.csf.janken.sdk.Game;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.gameplay.RoundEvent;
import ru.vsu.csf.janken.sdk.gameplay.RoundEventListener;

public class UiGameWrapper implements RoundEventListener {

    private final Game game;

    private final ObjectProperty<Figure> player1Figure = new SimpleObjectProperty<>();
    private final ObjectProperty<Figure> player2Figure = new SimpleObjectProperty<>();
    private final ObjectProperty<RoundResult> roundResult = new SimpleObjectProperty<>();


    public UiGameWrapper(Game game) {
        this.game = game;
        game.addRoundEventListener(this);
    }

    public RoundResult round() {
        return game.round();
    }

    @Override
    public void onRoundFinished(RoundEvent event) {
        roundResult.setValue(event.result());
        player1Figure.setValue(event.p1());
        player2Figure.setValue(event.p2());
    }

    public ObjectProperty<Figure> player1FigureProperty() {
        return player1Figure;
    }

    public ObjectProperty<Figure> player2FigureProperty() {
        return player2Figure;
    }

    public ObjectProperty<RoundResult> roundResultProperty() {
        return roundResult;
    }

}
