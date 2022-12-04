package ru.vsu.csf.janken.sdk.events;

import ru.vsu.csf.janken.sdk.enums.Figure;
import ru.vsu.csf.janken.sdk.enums.RoundResult;

public record RoundEvent (RoundResult result, Figure p1, Figure p2){}