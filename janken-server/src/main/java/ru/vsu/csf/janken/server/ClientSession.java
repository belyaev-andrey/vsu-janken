package ru.vsu.csf.janken.server;

import ru.vsu.csf.janken.sdk.Game;
import ru.vsu.csf.janken.sdk.LocalGame;
import ru.vsu.csf.janken.sdk.enums.GameCommand;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.gameplay.RandomPlayerStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSession implements Runnable {

    private final BufferedReader in;
    private final PrintWriter out;

    Socket socket;

    public ClientSession(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        NetworkStrategy networkStrategy = new NetworkStrategy();
        Game game = new LocalGame(new Player(networkStrategy), new Player(new RandomPlayerStrategy()));

        game.addRoundEventListener(event -> {
                    out.printf("%s:%s:%s%n",
                            event.result().getString(),
                            event.p1().getString(),
                            event.p2().getString());
                    out.flush();
                }
        );

        String command;
        boolean gameOver = false;
        while (!gameOver) {
            try {
                command = in.readLine();
                if (GameCommand.END.getString().equals(command)) {
                    gameOver = true;
                    socket.close();
                } else if (command != null) {
                    networkStrategy.setValue(command);
                    game.round();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
