package com.example.demo.model;

public class Action {
    private String type;
    private int portID;
    private double lat;
    private double lon;
    private int shipID;
    private int totalWeightCapacity;
    private int maxNumberOfAllContainers;
    private int maxNumberOfHeavyContainers;
    private int maxNumberOfRefrigeratedContainers;
    private int maxNumberOfLiquidContainers;
    private double fuelConsumptionPerKM;
    private int containerID;
    private int weight;
    private String containerType;
    private int destinationPortID;
    private double newFuel;


    public String getType() {
        return type;
    }

    public int getPortID() {
        return portID;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getShipID() {
        return shipID;
    }

    public int getTotalWeightCapacity() {
        return totalWeightCapacity;
    }

    public int getMaxNumberOfAllContainers() {
        return maxNumberOfAllContainers;
    }

    public int getMaxNumberOfHeavyContainers() {
        return maxNumberOfHeavyContainers;
    }

    public int getMaxNumberOfRefrigeratedContainers() {
        return maxNumberOfRefrigeratedContainers;
    }

    public int getMaxNumberOfLiquidContainers() {
        return maxNumberOfLiquidContainers;
    }

    public double getFuelConsumptionPerKM() {
        return fuelConsumptionPerKM;
    }

    public int getContainerID() {
        return containerID;
    }

    public int getWeight() {
        return weight;
    }

    public String getContainerType() {
        return containerType;
    }

    public int getDestinationPortID() {
        return destinationPortID;
    }

    public double getNewFuel() {
        return newFuel;
    }
}