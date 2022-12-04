package ru.vsu.csf.janken.sdk.events;

import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.network.NetworkCommand;

import java.util.Objects;

public final class RoundEvent implements NetworkCommand {
    private RoundResult result;
    private Figure p1;
    private Figure p2;

    public RoundEvent() {
    }

    public RoundEvent(RoundResult result, Figure p1, Figure p2) {
        this.result = result;
        this.p1 = p1;
        this.p2 = p2;
    }

    public RoundResult getResult() {
        return result;
    }

    public void setResult(RoundResult result) {
        this.result = result;
    }

    public Figure getP1() {
        return p1;
    }

    public void setP1(Figure p1) {
        this.p1 = p1;
    }

    public Figure getP2() {
        return p2;
    }

    public void setP2(Figure p2) {
        this.p2 = p2;
    }

    @Override
    public String getString() {
            return String.format("%s:%s:%s",
                            getResult().getString(),
                            getP1().getString(),
                            getP2().getString());
    }

    @Override
    public String toString() {
        return "RoundEvent[" +
                "result=" + result + ", " +
                "p1=" + p1 + ", " +
                "p2=" + p2 + ']';
    }
}