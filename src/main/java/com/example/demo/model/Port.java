package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Port implements IPort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private double latitude;
    private double longitude;
    @OneToMany(mappedBy = "port", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Container> containers = new ArrayList<>();

    @OneToMany(mappedBy = "currentPort", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ship> history = new ArrayList<>();

    @OneToMany(mappedBy = "currentPort", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ship> current = new ArrayList<>();


    public Port(int ID, double latitude, double longitude) {
        this.ID = ID;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Port() {

    }

    @Override
    public void incomingShip(Ship s) {
        if (!current.contains(s)) {
            current.add(s);
        }
    }

    @Override
    public void outgoingShip(Ship s) {
        current.remove(s);
        history.add(s);
    }

    public double getDistance(Port destinationPort) {
        double lat1 = Math.toRadians(this.getLatitude());
        double lon1 = Math.toRadians(this.getLongitude());
        double lat2 = Math.toRadians(destinationPort.getLatitude());
        double lon2 = Math.toRadians(destinationPort.getLongitude());

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double radius = 6371;

        return radius * c;
    }

    public int getID() {
        return ID;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public List<Ship> getHistory() {
        return history;
    }

    public List<Ship> getCurrent() {
        return current;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public void setHistory(List<Ship> history) {
        this.history = history;
    }

    public void setCurrent(List<Ship> current) {
        this.current = current;
    }
}
