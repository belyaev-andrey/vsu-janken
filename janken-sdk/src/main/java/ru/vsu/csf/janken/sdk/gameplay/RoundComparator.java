package ru.vsu.csf.janken.sdk.gameplay;

import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;

import static ru.vsu.csf.janken.sdk.enums.Figure.PAPER;
import static ru.vsu.csf.janken.sdk.enums.Figure.SCISSORS;

public class RoundComparator {

    public RoundResult compareResults(Figure figure1, Figure figure2) {
        if (figure1 == figure2) return RoundResult.DRAW;

        if (figure1 == Figure.ROCK) {
            switch (figure2) {
                case PAPER -> {
                    return RoundResult.LOSE;
                }
                case SCISSORS -> {
                    return RoundResult.WIN;
                }
            }
        } else if (figure1 == SCISSORS) {
            switch (figure2) {
                case PAPER -> {
                    return RoundResult.WIN;
                }
                case ROCK -> {
                    return RoundResult.LOSE;
                }
            }
        } else {
            switch (figure2) {
                case SCISSORS -> {
                    return RoundResult.LOSE;
                }
                case ROCK -> {
                    return RoundResult.WIN;
                }
            }
        }
        throw new IllegalArgumentException("Could not find a combination");
    }

}