package com.company.engine.utils;

import com.company.engine.events.SimEvent;

import java.util.Comparator;

public class SimEventComparator implements Comparator<SimEvent> {
    @Override
    public int compare(SimEvent t0, SimEvent t1) {
        if (t0.getRunTime() > t1.getRunTime()) {
            return 1;
        } else if (t0.getRunTime() == t1.getRunTime()) {
            if (t0.getSimPriority() > t1.getSimPriority()) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
