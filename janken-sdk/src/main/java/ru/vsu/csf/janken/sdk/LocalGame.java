package ru.vsu.csf.janken.sdk;

import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.gameplay.RoundComparator;
import ru.vsu.csf.janken.sdk.events.RoundEvent;

public class LocalGame extends AbstractGame {

    private final Player player2;

    private final RoundComparator roundComparator = new RoundComparator();

    public LocalGame(Player player1, Player player2) {
        super(player1);
        this.player2 = player2;
    }

    @Override
    protected RoundEvent doOnRound() {
        Figure player1Result = player1.play();
        Figure player2Result = player2.play();
        RoundResult roundResult = roundComparator.compareResults(player1Result, player2Result);
        RoundEvent event = new RoundEvent(roundResult, player1Result, player2Result);
        return event;
    }

    @Override
    protected void doOnEndGame() {
        //Nothing to do in the local version
    }
}