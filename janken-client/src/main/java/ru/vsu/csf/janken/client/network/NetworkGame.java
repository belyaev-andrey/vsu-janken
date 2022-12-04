package ru.vsu.csf.janken.client.network;

import ru.vsu.csf.janken.sdk.AbstractGame;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.GameCommand;
import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.events.RoundEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkGame extends AbstractGame {

    Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public NetworkGame(Player player1, String server, int port) {
        super(player1);
        try {
            socket = new Socket(server, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            //TODO Handle exception properly
            throw new RuntimeException(e);
        }
    }

    @Override
    protected RoundEvent doOnRound() {
        out.println(player1.getPlayerStrategy().getFigure().getString());
        String response;
        try {
            while ((response = in.readLine()) == null);
            System.out.println(response);
            String[] split = response.split(":");
            RoundEvent event = new RoundEvent(
                    RoundResult.valueOf(split[0]),
                    Figure.valueOf(split[1]),
                    Figure.valueOf(split[2]));
            return event;
        } catch (IOException ex) {
            //TODO handle exception
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void doOnEndGame() {
        out.println(GameCommand.END.getString());
        try {
            socket.close();
        } catch (IOException e) {
            //TODO handle error
            throw new RuntimeException(e);
        }
    }

}
