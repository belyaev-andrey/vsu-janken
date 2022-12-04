package ru.vsu.csf.janken.client;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.vsu.csf.janken.sdk.AbstractGame;
import ru.vsu.csf.janken.sdk.Game;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.events.GameOverEvent;
import ru.vsu.csf.janken.sdk.events.GameOverEventListener;
import ru.vsu.csf.janken.sdk.events.RoundEvent;
import ru.vsu.csf.janken.sdk.events.RoundEventListener;

import java.util.ArrayList;
import java.util.List;

public class UiGameWrapper implements RoundEventListener, GameOverEventListener {

    private Game game;

    private final ObjectProperty<Figure> player1Figure = new SimpleObjectProperty<>();
    private final ObjectProperty<Figure> player2Figure = new SimpleObjectProperty<>();
    private final ObjectProperty<RoundResult> roundResult = new SimpleObjectProperty<>();
    private final List<GameOverEventListener> gameOverEventListeners = new ArrayList<>();

    public UiGameWrapper(AbstractGame game) {
        this.game = game;
        game.addRoundEventListener(this);
        game.addGameOverEventListener(this);
    }

    public RoundEvent round() {
        return game.round();
    }

    public void endGame() {
        game.endGame();
    }

    public void setGame(AbstractGame game) {
        this.game = game;
        game.addRoundEventListener(this);
        game.addGameOverEventListener(this);
    }

    @Override
    public void onRoundFinished(RoundEvent event) {
        roundResult.setValue(event.getResult());
        player1Figure.setValue(event.getP1());
        player2Figure.setValue(event.getP2());
    }

    public void addGameOverListener(GameOverEventListener listener) {
        gameOverEventListeners.add(listener);
    }
    @Override
    public void onGameOver(GameOverEvent event) {
        gameOverEventListeners.forEach(l -> l.onGameOver(event));
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
