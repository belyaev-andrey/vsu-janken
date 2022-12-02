package ru.vsu.csf.janken.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import ru.vsu.csf.janken.sdk.Game;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.gameplay.RandomPlayerStrategy;
import ru.vsu.csf.janken.sdk.gameplay.RoundEvent;
import ru.vsu.csf.janken.sdk.gameplay.RoundEventListener;

public class GameController implements RoundEventListener {

    private Game game;
    private PlayerUiStrategy strategy;


    @FXML
    public void initialize() {
        //set avatars and names

        strategy = new PlayerUiStrategy();
        Player player1 = new Player(strategy);
        Player player2 = new Player(new RandomPlayerStrategy());
        this.game = new Game(player1, player2);

        //Adding listeners
        strategy.figureProperty().addListener((observable, oldValue, newValue) ->
                player1Figure.setText(newValue.figurePicture));
        player1Figure.setText(strategy.getFigure().figurePicture);

        game.addRoundEventListener(this);
    }

    @Override
    public void onRoundFinished(RoundEvent event) {
        player2Figure.setText(event.p2().figurePicture);
        gameStateLabel.setText(event.result().resultString);
    }

    @FXML
    public TextField player1Name;
    @FXML
    public Label player2Name;
    @FXML
    public Label player1Figure;
    @FXML
    public Label player2Figure;

    @FXML
    private Label gameStateLabel;

    @FXML
    private ImageView player1Avatar;

    @FXML
    private ImageView player2Avatar;

    @FXML
    protected void onRockButtonPressed() {
        strategy.setFigure(Figure.ROCK);
    }

    @FXML
    protected void onScissorsButtonPressed() {
        strategy.setFigure(Figure.SCISSORS);
    }

    @FXML
    protected void onPaperButtonPressed() {
        strategy.setFigure(Figure.PAPER);
    }

    @FXML
    protected void onPlayButtonPressed() {
        game.round();
    }

}