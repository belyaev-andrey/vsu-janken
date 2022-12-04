package ru.vsu.csf.janken.sdk.events;

import ru.vsu.csf.janken.sdk.events.RoundEvent;

public interface RoundEventListener {

    void onRoundFinished(RoundEvent event);

}