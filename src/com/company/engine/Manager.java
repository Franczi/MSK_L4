package com.company.engine;

import com.company.engine.events.CustomerArrival;
import com.company.engine.events.SimEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager {

    private static Manager instance;
    private double currentSimTime;
    private double finishSimTime;
    private double firstHandleClientTime;
    private List<Double> clientArrivalsTime= new ArrayList<>();
    private List<Double> clientHandlesTime= new ArrayList<>();
    private SimCalendar simCalendar;
    private int queueSize = 0;
    private int handledClients = 0;
    private int events = 0;

    private Manager() {
        simCalendar = new SimCalendar();
    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public double generateRandomDoubleInRange(double min, double max) {
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public void registerSimulationEvent(SimEvent event) {
        simCalendar.add(event);
    }

    public double simTime() {
        return currentSimTime;
    }

    double calculateAvgWaitingTime(){
        double sum=0;
        for (int i = 0; i < clientArrivalsTime.size(); i++) {
            double period = clientHandlesTime.get(i)-clientArrivalsTime.get(i);
            sum+=period;
        }
        return sum/clientArrivalsTime.size();
    }

    public void stopSimulation() {
        System.out.println("==SUMMARY==");
        double avgHandlingTime = (finishSimTime-firstHandleClientTime)/(double) handledClients;
        System.out.println("Average handling time: "+avgHandlingTime);
        System.out.println("Handled customers: "+handledClients);
        System.out.println("Average waiting time: "+calculateAvgWaitingTime());
    }

    public void startSimulation() {
        currentSimTime = 0.0;
        finishSimTime=2000.0;
        nextEvent();
    }


    public void nextEvent() {
        events++;
        System.out.println("==New Event " + events + "==");
        SimEvent event = simCalendar.getFirst();
        currentSimTime = event.getRunTime();
        System.out.println("current time: " + currentSimTime);
        if (event instanceof CustomerArrival) {
            clientArrivalsTime.add(currentSimTime);
            queueSize++;
            System.out.println("queueSize: " + queueSize);
            if (queueSize < 100 && currentSimTime<finishSimTime)
                event.stateChange();
            else
                nextEvent();
        } else {
            queueSize--;
            handledClients++;
            System.out.println("queue size: " + queueSize);
            clientHandlesTime.add(currentSimTime);
            if (handledClients == 0) firstHandleClientTime = currentSimTime;
            if (queueSize > 0) {
                event.stateChange();
            } else {
                finishSimTime=currentSimTime;
                stopSimulation();
            }
        }
    }
}
