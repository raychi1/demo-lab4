package com.example.demo.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private double weight;
    private int count;

    @ManyToOne
    @JoinColumn(name = "container_id")
    private Container container;

    public Item(int ID, double weight, int count, Container container) {
        this.ID = ID;
        this.weight = weight;
        this.count = count;
        this.container = container;
    }

    public Item() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public abstract double getTotalWeight();

    public class Small extends Item {
        public Small(int ID, double weight, int count, Container container) {
            super(ID, weight, count, container);
        }

        @Override
        public double getTotalWeight() {
            return (weight * count) / 1.5;
        }

    }

    public class Heavy extends Item {
        public Heavy(int ID, double weight, int count, Container container) {
            super(ID, weight, count, container);
        }

        @Override
        public double getTotalWeight() {
            return (weight * count) * 1.5;
        }

    }

    public class Refrigerated extends Item {
        public Refrigerated(int ID, double weight, int count, Container container) {
            super(ID, weight, count, container);
        }

        @Override
        public double getTotalWeight() {
            return ((weight * count) * 2) - 3;
        }

    }

    public class Liquid extends Item {
        public Liquid(int ID, double weight, int count, Container container) {
            super(ID, weight, count, container);
        }

        @Override
        public double getTotalWeight() {
            return weight * count;
        }
    }
}
