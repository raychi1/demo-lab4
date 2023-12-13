package com.example.demo;

import com.example.demo.model.Container;
import com.example.demo.model.Port;
import com.example.demo.model.Ship;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShippingSystemTest {

    @Test
    void testContainerEquality() {
        Container container1 = new Container.BasicContainer(1, 100);
        Container container2 = new Container.BasicContainer(1, 100);
        Container container3 = new Container.HeavyContainer(2, 200);

        assertTrue(container1.equals(container2));
        assertFalse(container1.equals(container3));
    }

    @Test
    void testShipSailing() {
        Port portA = mock(Port.class);
        Port portB = mock(Port.class);
        Ship ship = new Ship(1, 1000, portA, 5000, 20, 10, 5, 5, 0.1);

        when(portA.getDistance(portB)).thenReturn(1.0); // Mock the distance calculation

        portA.incomingShip(ship);

        assertTrue(ship.sailTo(portB));
        assertEquals(portB, ship.getCurrentPort());
    }

    @Test
    void testLoadUnloadContainer() {
        Port portA = mock(Port.class);
        Port portB = mock(Port.class);
        Ship ship = new Ship(1, 1000, portA, 5000, 20, 10, 5, 5, 0.1);

        when(portA.getDistance(portB)).thenReturn(1.0); // Mock the distance calculation

        portA.incomingShip(ship);

        Container container = mock(Container.class);
        when(container.getID()).thenReturn(1);
        when(container.getWeight()).thenReturn(100);

        List<Container> containersList = new ArrayList<>();
        containersList.add(container);

        when(portA.getContainers()).thenReturn(containersList);

        assertTrue(ship.load(container));
        assertTrue(ship.getContainers().contains(container));

        assertTrue(ship.unLoad(container));
        assertFalse(ship.getContainers().contains(container));
    }

    @Test
    void testReFuel() {
        Port portA = mock(Port.class);
        Ship ship = new Ship(1, 1000, portA, 5000, 20, 10, 5, 5, 0.1);

        double initialFuel = ship.getFuel();
        double refuelAmount = 100.0;

        ship.reFuel(refuelAmount);

        assertEquals(initialFuel + refuelAmount, ship.getFuel());
    }
}
