package ru.vsu.csf.janken.client.network;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import ru.vsu.csf.janken.sdk.AbstractGame;
import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;
import ru.vsu.csf.janken.sdk.events.RoundEvent;
import ru.vsu.csf.janken.sdk.gameplay.Player;

public class NetworkGame extends AbstractGame {

    private final RSocket rSocket;

    public NetworkGame(Player player1, String server, int port) {
        super(player1);
        rSocket = RSocketConnector.connectWith(TcpClientTransport.create(server, port)).block();
    }

    @Override
    protected RoundEvent doOnRound() {
        String command = player1.getPlayerStrategy().getFigure().getString();
        RoundEvent event = new RoundEvent();
        rSocket.requestResponse(DefaultPayload.create(command))
                .map(Payload::getDataUtf8)
                .doOnNext(s -> updateResponse(s, event))
                .block();
        return event;
    }

    private void updateResponse(String response, RoundEvent event) {
            String[] split = response.split(":");
            event.setResult(RoundResult.valueOf(split[0]));
            event.setP1(Figure.valueOf(split[1]));
            event.setP2(Figure.valueOf(split[2]));
    }

    @Override
    protected void doOnEndGame() {
        rSocket.dispose();
    }

}
