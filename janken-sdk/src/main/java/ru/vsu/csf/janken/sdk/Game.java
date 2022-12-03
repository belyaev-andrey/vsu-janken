package ru.vsu.csf.janken.sdk;

import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.gameplay.RoundComparator;
import ru.vsu.csf.janken.sdk.gameplay.RoundEvent;
import ru.vsu.csf.janken.sdk.gameplay.RoundEventListener;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Player player1;

    private final Player player2;

    private final List<RoundEventListener> listeners = new ArrayList<>();

    private final RoundComparator roundComparator = new RoundComparator();

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public RoundResult round() {
        Figure player1Result = player1.play();
        Figure player2Result = player2.play();
        RoundResult roundResult = roundComparator.compareResults(player1Result, player2Result);
        RoundEvent event = new RoundEvent(roundResult, player1Result, player2Result);
        listeners.forEach(l -> l.onRoundFinished(event));
        return roundResult;
    }

    public void addRoundEventListener(RoundEventListener listener) {
        listeners.add(listener);
    }

}