package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Entity
public class Ship implements IShip{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private double fuel;
    @ManyToOne
    @JoinColumn(name = "current_port_id")
    private Port currentPort;

    private int totalWeightCapacity;
    private int maxNumberOfAllContainers;
    private int maxNumberOfHeavyContainers;
    private int maxNumberOfRefrigeratedContainers;
    private int maxNumberOfLiquidContainers;
    private double fuelConsumptionPerKM;
    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Container> containers = new ArrayList<>();


    public Ship(int ID, double fuel, Port currentPort, int totalWeightCapacity,
                int maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
                int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers,
                double fuelConsumptionPerKM) {
        this.ID = ID;
        this.fuel = fuel;
        this.currentPort = currentPort;
        this.totalWeightCapacity = totalWeightCapacity;
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
        this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
        this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
    }

    public Ship() {

    }


    @Override
    public boolean sailTo(Port destinationPort) {
        if (currentPort.equals(destinationPort)) {
            System.out.println("Ship is already at the destination port.");
            return false;
        }

        double distance = currentPort.getDistance(destinationPort);

        double requiredFuel = distance * fuelConsumptionPerKM;

        if (fuel >= requiredFuel) {

            fuel -= requiredFuel;
            currentPort.outgoingShip(this);
            currentPort = destinationPort;
            destinationPort.incomingShip(this);

            System.out.println("Ship successfully sailed to port " + destinationPort.getID());
            return true;
        } else {
            System.out.println("Insufficient fuel to sail to port " + destinationPort.getID());
            return false;
        }
    }
    @Override
    public void reFuel(double newFuel) {
        fuel += newFuel;
    }
    @Override
    public boolean load(Container cont) {
        if (containers.size() >= maxNumberOfAllContainers) {
            System.out.println("Ship has reached maximum capacity for all containers.");
            return false;
        }

        if (cont instanceof Container.HeavyContainer && containers.stream().filter(c -> c instanceof Container.HeavyContainer).count() >= maxNumberOfHeavyContainers) {
            System.out.println("Ship has reached maximum capacity for HeavyContainers.");
            return false;
        }

        if (cont instanceof Container.RefrigeratedContainer && containers.stream().filter(c -> c instanceof Container.RefrigeratedContainer).count() >= maxNumberOfRefrigeratedContainers) {
            System.out.println("Ship has reached maximum capacity for RefrigeratedContainers.");
            return false;
        }

        if (cont instanceof Container.LiquidContainer && containers.stream().filter(c -> c instanceof Container.LiquidContainer).count() >= maxNumberOfLiquidContainers) {
            System.out.println("Ship has reached maximum capacity for LiquidContainers.");
            return false;
        }

        containers.add(cont);
        System.out.println("Container " + cont.getID() + " successfully loaded onto the ship.");
        return true;
    }
    @Override
    public boolean unLoad(Container cont) {
        if (containers.contains(cont)) {
            containers.remove(cont);
            System.out.println("Container " + cont.getID() + " successfully unloaded from the ship.");
            return true;
        } else {
            System.out.println("Container " + cont.getID() + " not found on the ship. Unloading failed.");
            return false;
        }
    }

    public List<Container> getCurrentContainers() {
        containers.sort(Comparator.comparingInt(Container::getID));
        return containers;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public int getTotalWeightCapacity() {
        return totalWeightCapacity;
    }

    public void setTotalWeightCapacity(int totalWeightCapacity) {
        this.totalWeightCapacity = totalWeightCapacity;
    }

    public int getMaxNumberOfAllContainers() {
        return maxNumberOfAllContainers;
    }

    public void setMaxNumberOfAllContainers(int maxNumberOfAllContainers) {
        this.maxNumberOfAllContainers = maxNumberOfAllContainers;
    }

    public int getMaxNumberOfHeavyContainers() {
        return maxNumberOfHeavyContainers;
    }

    public void setMaxNumberOfHeavyContainers(int maxNumberOfHeavyContainers) {
        this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
    }

    public int getMaxNumberOfRefrigeratedContainers() {
        return maxNumberOfRefrigeratedContainers;
    }

    public void setMaxNumberOfRefrigeratedContainers(int maxNumberOfRefrigeratedContainers) {
        this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
    }

    public int getMaxNumberOfLiquidContainers() {
        return maxNumberOfLiquidContainers;
    }

    public void setMaxNumberOfLiquidContainers(int maxNumberOfLiquidContainers) {
        this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
    }

    public double getFuelConsumptionPerKM() {
        return fuelConsumptionPerKM;
    }

    public void setFuelConsumptionPerKM(double fuelConsumptionPerKM) {
        this.fuelConsumptionPerKM = fuelConsumptionPerKM;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    ///////////////////////////////////
    public static class LightWeightShip extends Ship {

        public LightWeightShip(int ID, double fuel, Port currentPort, int totalWeightCapacity,
                               int maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
                               int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers,
                               double fuelConsumptionPerKM) {
            super(ID, fuel, currentPort, totalWeightCapacity,
                    maxNumberOfAllContainers, maxNumberOfHeavyContainers,
                    maxNumberOfRefrigeratedContainers, maxNumberOfLiquidContainers,
                    fuelConsumptionPerKM);
        }
    }

    public static class MediumShip extends Ship {

        public MediumShip(int ID, double fuel, Port currentPort, int totalWeightCapacity,
                          int maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
                          int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers,
                          double fuelConsumptionPerKM) {
            super(ID, fuel, currentPort, totalWeightCapacity,
                    maxNumberOfAllContainers, maxNumberOfHeavyContainers,
                    maxNumberOfRefrigeratedContainers, maxNumberOfLiquidContainers,
                    fuelConsumptionPerKM);
        }
    }

    public static class HeavyShip extends Ship {

        public HeavyShip(int ID, double fuel, Port currentPort, int totalWeightCapacity,
                         int maxNumberOfAllContainers, int maxNumberOfHeavyContainers,
                         int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers,
                         double fuelConsumptionPerKM) {
            super(ID, fuel, currentPort, totalWeightCapacity,
                    maxNumberOfAllContainers, maxNumberOfHeavyContainers,
                    maxNumberOfRefrigeratedContainers, maxNumberOfLiquidContainers,
                    fuelConsumptionPerKM);
        }
    }

    /////////////////////////////
    public class ShipBuilder {
        private int ID;
        private double fuel;
        private Port currentPort;
        private int totalWeightCapacity;
        private int maxNumberOfAllContainers;
        private int maxNumberOfHeavyContainers;
        private int maxNumberOfRefrigeratedContainers;
        private int maxNumberOfLiquidContainers;
        private double fuelConsumptionPerKM;

        public ShipBuilder(int ID, Port currentPort) {
            this.ID = ID;
            this.currentPort = currentPort;
        }

        public ShipBuilder fuel(double fuel) {
            this.fuel = fuel;
            return this;
        }

        public ShipBuilder totalWeightCapacity(int totalWeightCapacity) {
            this.totalWeightCapacity = totalWeightCapacity;
            return this;
        }

        public ShipBuilder maxNumberOfAllContainers(int maxNumberOfAllContainers) {
            this.maxNumberOfAllContainers = maxNumberOfAllContainers;
            return this;
        }

        public ShipBuilder maxNumberOfHeavyContainers(int maxNumberOfHeavyContainers) {
            this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
            return this;
        }

        public ShipBuilder maxNumberOfRefrigeratedContainers(int maxNumberOfRefrigeratedContainers) {
            this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
            return this;
        }

        public ShipBuilder maxNumberOfLiquidContainers(int maxNumberOfLiquidContainers) {
            this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
            return this;
        }

        public ShipBuilder fuelConsumptionPerKM(double fuelConsumptionPerKM) {
            this.fuelConsumptionPerKM = fuelConsumptionPerKM;
            return this;
        }

        public Ship build() {
            return new Ship(ID, fuel, currentPort, totalWeightCapacity,
                    maxNumberOfAllContainers, maxNumberOfHeavyContainers,
                    maxNumberOfRefrigeratedContainers, maxNumberOfLiquidContainers,
                    fuelConsumptionPerKM);
        }
    }

    public class LightWeightShipFactory implements ShipFactory {
        @Override
        public Ship createShip() {
            return new ShipBuilder(0, null)
                    .totalWeightCapacity(1000)
                    .maxNumberOfAllContainers(30)
                    .maxNumberOfHeavyContainers(10)
                    .maxNumberOfRefrigeratedContainers(5)
                    .maxNumberOfLiquidContainers(8)
                    .fuelConsumptionPerKM(0.08)
                    .build();
        }
    }

    public class MediumShipFactory implements ShipFactory {
        @Override
        public Ship createShip() {
            return new ShipBuilder(0, null)
                    .totalWeightCapacity(2000)
                    .maxNumberOfAllContainers(50)
                    .maxNumberOfHeavyContainers(20)
                    .maxNumberOfRefrigeratedContainers(10)
                    .maxNumberOfLiquidContainers(15)
                    .fuelConsumptionPerKM(0.05)
                    .build();
        }
    }

    public class HeavyShipFactory implements ShipFactory {
        @Override
        public Ship createShip() {
            return new ShipBuilder(0, null)
                    .totalWeightCapacity(3000)
                    .maxNumberOfAllContainers(70)
                    .maxNumberOfHeavyContainers(30)
                    .maxNumberOfRefrigeratedContainers(15)
                    .maxNumberOfLiquidContainers(20)
                    .fuelConsumptionPerKM(0.03)
                    .build();
        }
    }
}
