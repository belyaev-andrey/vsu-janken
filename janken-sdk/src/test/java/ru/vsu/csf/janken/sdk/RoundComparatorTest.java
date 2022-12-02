package ru.vsu.csf.janken.sdk;

import org.junit.jupiter.api.Test;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.gameplay.RoundComparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoundComparatorTest {

    @Test
    public void testComparatorDraw() {
        RoundComparator comparator = new RoundComparator();
        assertEquals(RoundResult.DRAW, comparator.compareResults(Figure.ROCK, Figure.ROCK));
        assertEquals(RoundResult.DRAW, comparator.compareResults(Figure.SCISSORS, Figure.SCISSORS));
        assertEquals(RoundResult.DRAW, comparator.compareResults(Figure.PAPER, Figure.PAPER));
    }

    @Test
    public void testComparatorWin() {
        RoundComparator comparator = new RoundComparator();
        assertEquals(RoundResult.WIN, comparator.compareResults(Figure.ROCK, Figure.SCISSORS));
        assertEquals(RoundResult.WIN, comparator.compareResults(Figure.SCISSORS, Figure.PAPER));
        assertEquals(RoundResult.WIN, comparator.compareResults(Figure.PAPER, Figure.ROCK));
    }

    @Test
    public void testComparatorLose() {
        RoundComparator comparator = new RoundComparator();
        assertEquals(RoundResult.LOSE, comparator.compareResults(Figure.ROCK, Figure.PAPER));
        assertEquals(RoundResult.LOSE, comparator.compareResults(Figure.SCISSORS, Figure.ROCK));
        assertEquals(RoundResult.LOSE, comparator.compareResults(Figure.PAPER, Figure.SCISSORS));
    }

}