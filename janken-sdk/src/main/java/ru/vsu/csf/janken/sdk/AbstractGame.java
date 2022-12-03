package ru.vsu.csf.janken.sdk;

import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.gameplay.RoundEventListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGame implements Game {
    protected final Player player1;
    protected final List<RoundEventListener> listeners = new ArrayList<>();

    public AbstractGame(Player player1) {
        this.player1 = player1;
    }

    @Override
    public void addRoundEventListener(RoundEventListener listener) {
        listeners.add(listener);
    }
}
