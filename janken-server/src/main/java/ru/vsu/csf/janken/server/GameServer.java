package ru.vsu.csf.janken.server;

import io.rsocket.Payload;
import io.rsocket.SocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import io.rsocket.util.EmptyPayload;
import reactor.core.publisher.Mono;
import ru.vsu.csf.janken.sdk.Game;
import ru.vsu.csf.janken.sdk.LocalGame;
import ru.vsu.csf.janken.sdk.enums.GameCommand;
import ru.vsu.csf.janken.sdk.events.RoundEvent;
import ru.vsu.csf.janken.sdk.gameplay.Player;
import ru.vsu.csf.janken.sdk.gameplay.RandomPlayerStrategy;

import java.io.IOException;

public class GameServer {

    private final NetworkStrategy networkStrategy;
    private final Game game;

    public static void main(String[] args) throws IOException {
        GameServer server = new GameServer(9999);
        server.start();
    }

    private final int port;


    public GameServer(int port) {
        this.port = port;
        networkStrategy = new NetworkStrategy();
        game = new LocalGame(new Player(networkStrategy), new Player(new RandomPlayerStrategy()));
    }

    protected Mono<Payload> requestHandler(Payload p) {
        String command = p.getDataUtf8();
        if (GameCommand.END.getString().equals(command)) {
            return Mono.just(EmptyPayload.INSTANCE);
        }
        networkStrategy.setValue(command);
        RoundEvent event = game.round();
        return Mono.just(DefaultPayload.create(event.getString()));
    }

    public void start() {
        System.out.printf("Server started on: %d%n", port);
        RSocketServer.create(SocketAcceptor.forRequestResponse(this::requestHandler))
                .payloadDecoder(PayloadDecoder.ZERO_COPY)
                .bindNow(TcpServerTransport.create(port)).onClose().block();
    }


}
