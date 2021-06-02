package com.company.engine;

import com.company.engine.events.SimEvent;
import com.company.engine.utils.SimEventComparator;

import java.util.PriorityQueue;

public class SimCalendar {
    private PriorityQueue<SimEvent> simEventQueue;

    public SimCalendar() {
        SimEventComparator comparator = new SimEventComparator();
        this.simEventQueue = new PriorityQueue<>(comparator);
    }

    void add(SimEvent event) {
        simEventQueue.add(event);
    }

    SimEvent getFirst() {
        return simEventQueue.poll();
    }

    public void remove() {
        simEventQueue.clear();
    }
}
