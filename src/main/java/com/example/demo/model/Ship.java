package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ships")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int capacity;

    @OneToMany(mappedBy = "ship")
    private List<Container> containers;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public static class Builder {
        private String name;
        private List<Container> containers;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder containers(List<Container> containers) {
            this.containers = containers;
            return this;
        }

        public Ship build() {
            Ship ship = new Ship();
            ship.setName(this.name);
            ship.setContainers(this.containers);
            return ship;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ship ship = (Ship) o;
        return Objects.equals(id, ship.id)
                && Objects.equals(name, ship.name)
                && Objects.equals(capacity, ship.capacity)
                && Objects.equals(containers, ship.containers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, containers);
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", containers=" + containers +
                '}';
    }
}