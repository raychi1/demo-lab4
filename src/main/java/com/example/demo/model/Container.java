package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public abstract class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private int weight;

    @ManyToOne
    @JoinColumn(name = "port_id")
    private Port port;

    @ManyToOne
    @JoinColumn(name = "ship_id") // Adjust the column name if needed
    private Ship ship;

    public Container(int ID, int weight) {
        this.ID = ID;
        this.weight = weight;
    }

    public Container() {

    }

    // Abstract method to be implemented by subclasses
    public abstract double consumption();

    public boolean equals(Container other) {
        // Check if the type, ID, and weight are the same
        return getClass().equals(other.getClass()) && ID == other.getID() && weight == other.getWeight();
    }

    // Getter methods for private fields
    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }
    public int getID() {
        return ID;
    }

    public int getWeight() {
        return weight;
    }

    // BasicContainer class
    public static class BasicContainer extends Container {
        public BasicContainer(int ID, int weight) {
            super(ID, weight);
        }

        @Override
        public double consumption() {
            // Fuel consumption for BasicContainer: 2.50 per unit of weight
            return 2.50 * getWeight();
        }
    }

    // HeavyContainer class
    public static class HeavyContainer extends Container {
        public HeavyContainer(int ID, int weight) {
            super(ID, weight);
        }

        @Override
        public double consumption() {
            // Fuel consumption for HeavyContainer: 3.00 per unit of weight
            return 3.00 * getWeight();
        }
    }

    // RefrigeratedContainer class
    public static class RefrigeratedContainer extends HeavyContainer {
        public RefrigeratedContainer(int ID, int weight) {
            super(ID, weight);
        }

        @Override
        public double consumption() {
            // Fuel consumption for RefrigeratedContainer: 5.00 per unit of weight
            return 5.00 * getWeight();
        }
    }

    // LiquidContainer class
    public static class LiquidContainer extends HeavyContainer {
        public LiquidContainer(int ID, int weight) {
            super(ID, weight);
        }

        @Override
        public double consumption() {
            // Fuel consumption for LiquidContainer: 4.00 per unit of weight
            return 4.00 * getWeight();
        }

    }
}
