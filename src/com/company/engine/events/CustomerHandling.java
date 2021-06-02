package com.company.engine.events;

public class CustomerHandling extends SimEvent {

    @Override
    public void stateChange() {
        SimEvent event = new CustomerHandling();
        getManager().registerSimulationEvent(event);
        getManager().nextEvent();
    }

    public CustomerHandling() {
        super();
        setSimPriority(2);
        setRunTime(getManager().simTime() + getManager().generateRandomDoubleInRange(2.0, 4.0));
    }
}
