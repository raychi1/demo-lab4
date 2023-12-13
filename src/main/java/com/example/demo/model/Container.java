package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "container_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private int weight;

    @ManyToOne
    @JoinColumn(name = "port_id")
    private Port port;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    public Container(int ID, int weight) {
        this.ID = ID;
        this.weight = weight;
    }

    public Container() {

    }

    public abstract double consumption();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return ID == container.ID && weight == container.weight && Objects.equals(port, container.port) && Objects.equals(ship, container.ship);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, weight, port, ship);
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

    @Entity
    @Table(name = "heavy_container")
    // HeavyContainer class
    public static class HeavyContainer extends Container {
        public HeavyContainer(int ID, int weight) {
            super(ID, weight);
        }

        public HeavyContainer() {

        }

        @Override
        public double consumption() {
            return 3.00 * getWeight();
        }
    }

    // RefrigeratedContainer class
    @Entity
    @Table(name = "refrigerated_container")
    public static class RefrigeratedContainer extends HeavyContainer {
        public RefrigeratedContainer(int ID, int weight) {
            super(ID, weight);
        }

        public RefrigeratedContainer() {

        }

        @Override
        public double consumption() {
            return 5.00 * getWeight();
        }
    }

    // LiquidContainer class
    @Entity
    @Table(name = "liquid_container")
    public static class LiquidContainer extends HeavyContainer {
        public LiquidContainer(int ID, int weight) {
            super(ID, weight);
        }

        public LiquidContainer() {

        }

        @Override
        public double consumption() {
            return 4.00 * getWeight();
        }

    }
}
