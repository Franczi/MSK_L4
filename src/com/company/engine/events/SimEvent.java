package com.company.engine.events;

import com.company.engine.Manager;

public abstract class SimEvent {

    private double runTime;
    private int simPriority;
    private Manager manager;

    public abstract void stateChange();

    public SimEvent() {
        manager = Manager.getInstance();
    }

    public double getRunTime() {
        return runTime;
    }

    public void setRunTime(double runTime) {
        this.runTime = runTime;
    }

    public int getSimPriority() {
        return simPriority;
    }

    public void setSimPriority(int simPriority) {
        this.simPriority = simPriority;
    }

    public Manager getManager() {
        return manager;
    }
}
