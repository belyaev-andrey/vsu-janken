package ru.vsu.csf.janken.client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import ru.vsu.csf.janken.client.network.NetworkGame;
import ru.vsu.csf.janken.sdk.LocalGame;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.gameplay.RandomPlayerStrategy;

public class GameController {

    private UiGameWrapper wrapper;

    public ToggleButton rockButton;
    public ToggleButton scissorBtn;
    public ToggleButton paperButton;

    public Button playButton;
    public TextField player1Name;
    public Label player2Name;
    public Label player1Figure;
    public Label player2Figure;
    public Label gameStateLabel;

    @FXML
    public void initialize() {

        resetUi();

        wrapper = new UiGameWrapper(new LocalGame(new Player(createPlayerStrategy()),
                new Player(new RandomPlayerStrategy())));

        wrapper.player1FigureProperty().addListener((observable, oldValue, newValue) -> {
            player1Figure.setText(newValue.getPicture());
        });

        wrapper.player2FigureProperty().addListener((observable, oldValue, newValue) -> {
            player2Figure.setText(newValue.getPicture());
        });

        wrapper.roundResultProperty().addListener((observable, oldValue, newValue) -> {
            gameStateLabel.setText(newValue.getString());
        });

        wrapper.addGameOverListener((e) -> {
            resetUi();
        });

    }

    private PlayerUiStrategy createPlayerStrategy() {
        PlayerUiStrategy strategy = new PlayerUiStrategy();
        //Adding listener to strategy model
        strategy.figureProperty().addListener((observable, oldValue, newValue) -> {
                    playButton.setDisable(false);
                    player2Figure.setText("");
                    gameStateLabel.setText("Press Play To Start");
                    player1Figure.setText(newValue.getPicture());
                }
        );
        //now buttons will update new strategy
        rockButton.setOnAction(event -> strategy.setFigure(Figure.ROCK));
        scissorBtn.setOnAction(event -> strategy.setFigure(Figure.SCISSORS));
        paperButton.setOnAction(event -> strategy.setFigure(Figure.PAPER));
        return strategy;
    }

    private void resetUi() {
        rockButton.setSelected(false);
        scissorBtn.setSelected(false);
        paperButton.setSelected(false);
        playButton.setDisable(true);
        player1Figure.setText("");
        player2Figure.setText("");
        gameStateLabel.setText("Press Play To Start");
    }

    @FXML
    protected void onPlayButtonPressed() {
        wrapper.round();
    }

    @FXML
    protected void onLocalGameButtonPressed() {
        wrapper.endGame();
        wrapper.setGame(new LocalGame(new Player(createPlayerStrategy()), new Player(new RandomPlayerStrategy())));
    }

    @FXML
    protected void onRemoteGameButtonPressed() {
        wrapper.endGame();
        wrapper.setGame(new NetworkGame(new Player(createPlayerStrategy()), "localhost", 9999));
    }

}