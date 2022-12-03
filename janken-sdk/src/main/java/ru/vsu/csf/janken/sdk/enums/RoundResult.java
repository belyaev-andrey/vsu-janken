package ru.vsu.csf.janken.sdk.enums;

import ru.vsu.csf.janken.sdk.network.NetworkCommand;

public enum RoundResult implements NetworkCommand {
    
    WIN("WIN"),
    LOSE("LOSE"),
    DRAW("DRAW");
    
    private final String resultString;

    RoundResult(String resultString) {
        this.resultString = resultString;
    }

    @Override
    public String getString() {
        return resultString;
    }
}