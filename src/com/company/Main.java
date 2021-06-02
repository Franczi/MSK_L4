package com.company;

import com.company.engine.Manager;
import com.company.engine.events.CustomerArrival;
import com.company.engine.events.CustomerHandling;
import com.company.engine.events.SimEvent;

public class Main {

    public static void main(String[] args) {
        SimEvent event1 = new CustomerArrival();
        SimEvent event2 = new CustomerHandling();
        event2.setRunTime(event2.getRunTime()+event1.getRunTime());
        Manager.getInstance().registerSimulationEvent(event1);
        Manager.getInstance().registerSimulationEvent(event2);
        Manager.getInstance().startSimulation();
    }
}
