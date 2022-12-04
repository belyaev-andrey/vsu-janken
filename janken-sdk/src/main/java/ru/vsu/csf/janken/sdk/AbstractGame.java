package ru.vsu.csf.janken.sdk;

import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.events.GameOverEvent;
import ru.vsu.csf.janken.sdk.events.GameOverEventListener;
import ru.vsu.csf.janken.sdk.events.RoundEvent;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.events.RoundEventListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame implements Game {
    protected final Player player1;
    protected final List<RoundEventListener> listeners = new ArrayList<>();

    protected final List<GameOverEventListener> gameOverEventListeners = new ArrayList<>();

    public AbstractGame(Player player1) {
        this.player1 = player1;
    }

    @Override
    public void addRoundEventListener(RoundEventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void addGameOverEventListener(GameOverEventListener listener) {
        gameOverEventListeners.add(listener);
    }

    protected abstract RoundEvent doOnRound(); //Implemented to avoid copying listeners to all game instances

    @Override
    public RoundEvent round() {
        RoundEvent event = doOnRound();
        System.out.println("Round result: "+event);
        listeners.forEach(l -> l.onRoundFinished(event));
        return event;
    }

    protected abstract void doOnEndGame();//Implemented to avoid copying listeners to all game instances

    @Override
    public void endGame() {
        doOnEndGame();
        GameOverEvent event = new GameOverEvent();
        gameOverEventListeners.forEach(l -> l.onGameOver(event));
    }
}
