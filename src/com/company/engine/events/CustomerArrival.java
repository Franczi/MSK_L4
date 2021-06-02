package com.company.engine.events;

public class CustomerArrival extends SimEvent {
    @Override
    public void stateChange() {
        SimEvent event = new CustomerArrival();
        getManager().registerSimulationEvent(event);
        getManager().nextEvent();
    }

    public CustomerArrival() {
        super();
        setSimPriority(1);
        setRunTime(getManager().simTime()+3.0);
    }
}
