package ru.vsu.csf.janken.sdk.enums;

import ru.vsu.csf.janken.sdk.Drawable;
import ru.vsu.csf.janken.sdk.network.NetworkCommand;

public enum GameCommand implements NetworkCommand, Drawable {

    START("START", "⏱️"),
    END ("END", "🏁");

    public final String cmdString;
    public final String cmdPicture;
    GameCommand(String cmdString, String cmdPicture) {
        this.cmdString = cmdString;
        this.cmdPicture = cmdPicture;
    }


    @Override
    public String getPicture() {
        return cmdPicture;
    }

    @Override
    public String getString() {
        return cmdString;
    }
}