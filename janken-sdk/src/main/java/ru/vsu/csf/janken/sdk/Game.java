package ru.vsu.csf.janken.sdk;

import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.gameplay.RoundEventListener;

public interface Game {
    RoundResult round();

    void addRoundEventListener(RoundEventListener listener);

    default void endGame() {}
}
