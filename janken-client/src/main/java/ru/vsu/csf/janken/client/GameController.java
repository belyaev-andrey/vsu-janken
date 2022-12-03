package ru.vsu.csf.janken.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import ru.vsu.csf.janken.sdk.Game;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.gameplay.RandomPlayerStrategy;

public class GameController {

    private UiGameWrapper wrapper;
    public ToggleButton rockButton;
    public ToggleButton scissorBtn;
    public ToggleButton paperButton;
    public TextField player1Name;
    public Label player2Name;
    public Label player1Figure;
    public Label player2Figure;
    public Label gameStateLabel;

    @FXML
    public void initialize() {

        PlayerUiStrategy player1Strategy = new PlayerUiStrategy();
        wrapper = new UiGameWrapper(new Game(new Player(player1Strategy), new Player(new RandomPlayerStrategy())));


        //Adding listener to player1Strategy model
        player1Strategy.figureProperty().addListener((observable, oldValue, newValue) -> {
                    player1Figure.setText(newValue.getPicture());
                    player2Figure.setText("");
                    gameStateLabel.setText("Press Play To Start");
                }
        );

        rockButton.setOnAction(event -> player1Strategy.setFigure(Figure.ROCK));
        scissorBtn.setOnAction(event -> player1Strategy.setFigure(Figure.SCISSORS));
        paperButton.setOnAction(event -> player1Strategy.setFigure(Figure.PAPER));

        wrapper.player1FigureProperty().addListener((observable, oldValue, newValue) -> {
            player1Figure.setText(newValue.getPicture());
        });

        wrapper.player2FigureProperty().addListener((observable, oldValue, newValue) -> {
            player2Figure.setText(newValue.getPicture());
        });

        wrapper.roundResultProperty().addListener((observable, oldValue, newValue) -> {
            gameStateLabel.setText(newValue.getString());
        });

    }

    @FXML
    protected void onPlayButtonPressed() {
        wrapper.round();
    }

    @FXML
    protected void onLocalGameButtonPressed() {
        //TODO start local game
    }

    @FXML
    protected void onRemoteGameButtonPressed() {
        //TODO start remote game
    }

}