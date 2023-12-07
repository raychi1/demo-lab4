package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "containers")
public class Container {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "port_id")
    private Port port;

    @OneToMany(mappedBy = "container")
    private List<Item> items;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Objects.equals(id, container.id)
                && Objects.equals(ship, container.ship)
                && Objects.equals(port, container.port)
                && Objects.equals(items, container.items)
                && Objects.equals(name, container.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ship, port, items, name);
    }

    @Override
    public String toString() {
        return "Container{" +
                "id=" + id +
                ", ship=" + ship +
                ", port=" + port +
                ", items=" + items +
                ", name='" + name + '\'' +
                '}';
    }
}
