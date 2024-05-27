package com.intellexi.racequery.event.strategy;

import com.intellexi.racequery.event.enums.EventType;

public interface EventStrategy<E> {

    boolean isSatisfied(Object event);

    void handleEvent(E event);

}
