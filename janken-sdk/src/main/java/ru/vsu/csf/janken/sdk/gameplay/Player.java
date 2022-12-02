package ru.vsu.csf.janken.sdk.gameplay;

import ru.vsu.csf.janken.sdk.enums.Figure;

public class Player {

    private final PlayerStrategy playerStrategy;

    public Player(PlayerStrategy playerStrategy) {
        this.playerStrategy = playerStrategy;
    }

    public Figure play() {
        return playerStrategy.getFigure();
    }

    public PlayerStrategy getPlayerStrategy() {
        return playerStrategy;
    }
}