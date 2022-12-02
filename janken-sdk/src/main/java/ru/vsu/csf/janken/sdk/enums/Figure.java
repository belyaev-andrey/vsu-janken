package ru.vsu.csf.janken.sdk.enums;

import ru.vsu.csf.janken.sdk.Drawable;
import ru.vsu.csf.janken.sdk.network.NetworkCommand;

import java.util.Random;

public enum Figure implements NetworkCommand, Drawable {

    ROCK("ROCK", "🪨"),
    SCISSORS("SCISSORS", "✂️"),
    PAPER("PAPER", "📜");

    public final String figureString;
    public final String figurePicture;
    
    private 
    
    Figure(String figureString, String figurePicture) {
        this.figureString = figureString;
        this.figurePicture = figurePicture;
    }
    
    private static final Random PRNG = new Random();

    public static Figure randomFigure()  {
        Figure[] figures = values();
        return figures[PRNG.nextInt(figures.length)];
    }


    @Override
    public String getPicture() {
        return figurePicture;
    }

    @Override
    public String getString() {
        return figureString;
    }
}