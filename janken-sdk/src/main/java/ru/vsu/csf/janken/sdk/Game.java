package ru.vsu.csf.janken.sdk;

import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.events.GameOverEventListener;
import ru.vsu.csf.janken.sdk.events.RoundEventListener;

public interface Game {
    RoundResult round();

    default void addRoundEventListener(RoundEventListener listener){};

    default void addGameOverEventListener(GameOverEventListener listener){};

    default void endGame() {}
}
