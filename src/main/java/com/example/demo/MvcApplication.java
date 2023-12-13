package com.example.demo;

import com.example.demo.model.Action;
import com.example.demo.model.Container;
import com.example.demo.model.Port;
import com.example.demo.model.Ship;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class MvcApplication {


    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
//        // Initialize ports and ships
//        Map<Integer, Port> ports = new HashMap<>();
//        Map<Integer, Ship> ships = new HashMap<>();
//
//        try {
//            // Read input JSON
//            ObjectMapper mapper = new ObjectMapper();
//            List<Action> actions = mapper.readValue(new File("input.json"), new TypeReference<List<Action>>() {
//            });
//
//            // Process actions
//            for (Action action : actions) {
//                switch (action.getType()) {
//                    case "port":
//                        createPort(ports, action);
//                        break;
//                    case "ship":
//                        createShip(ports, ships, action);
//                        break;
//                    case "container":
//                        createContainer(ports, ships, action);
//                        break;
//                    case "load":
//                        loadContainer(ports, ships, action);
//                        break;
//                    case "unload":
//                        unloadContainer(ports, ships, action);
//                        break;
//                    case "sail":
//                        sailToPort(ports, ships, action);
//                        break;
//                    case "refuel":
//                        refuelShip(ships, action);
//                        break;
//                }
//            }
//
//            // Print the result
//            printResult(ports, ships);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void createPort(Map<Integer, Port> ports, Action action) {
//        int portID = action.getPortID();
//        double lat = action.getLat();
//        double lon = action.getLon();
//        ports.put(portID, new Port(portID, lat, lon));
//    }
//
//    private static void createShip(Map<Integer, Port> ports, Map<Integer, Ship> ships, Action action) {
//        int shipID = action.getShipID();
//        int portID = action.getPortID();
//        Port port = ports.get(portID);
//
//        int totalWeightCapacity = action.getTotalWeightCapacity();
//        int maxNumberOfAllContainers = action.getMaxNumberOfAllContainers();
//        int maxNumberOfHeavyContainers = action.getMaxNumberOfHeavyContainers();
//        int maxNumberOfRefrigeratedContainers = action.getMaxNumberOfRefrigeratedContainers();
//        int maxNumberOfLiquidContainers = action.getMaxNumberOfLiquidContainers();
//        double fuelConsumptionPerKM = action.getFuelConsumptionPerKM();
//
//        Ship ship = new Ship(shipID, 0, port, totalWeightCapacity, maxNumberOfAllContainers,
//                maxNumberOfHeavyContainers, maxNumberOfRefrigeratedContainers, maxNumberOfLiquidContainers,
//                fuelConsumptionPerKM);
//
//        ships.put(shipID, ship);
//        port.incomingShip(ship);
//    }
//
//    private static void createContainer(Map<Integer, Port> ports, Map<Integer, Ship> ships, Action action) {
//        int containerID = action.getContainerID();
//        int weight = action.getWeight();
//        String containerType = action.getContainerType();
//
//        Container container;
//
//        if (weight <= 3000 || containerType.equals("R") || containerType.equals("L")) {
//            container = new Container.BasicContainer(containerID, weight);
//        } else {
//            if (containerType.equals("R")) {
//                container = new Container.RefrigeratedContainer(containerID, weight);
//            } else if (containerType.equals("L")) {
//                container = new Container.LiquidContainer(containerID, weight);
//            } else {
//                container = new Container.HeavyContainer(containerID, weight);
//            }
//        }
//
//        // Place the container in the port
//        ports.get(action.getPortID()).getContainers().add(container);
//    }
//
//    private static void loadContainer(Map<Integer, Port> ports, Map<Integer, Ship> ships, Action action) {
//        int shipID = action.getShipID();
//        int containerID = action.getContainerID();
//
//        Ship ship = ships.get(shipID);
//        Container container = findContainerInPorts(ports, containerID);
//
//        if (container != null && ship != null && ship.load(container)) {
//            // Remove the container from the port
//            ports.get(ship.getCurrentPort().getID()).getContainers().remove(container);
//        }
//    }
//
//    private static void unloadContainer(Map<Integer, Port> ports, Map<Integer, Ship> ships, Action action) {
//        int shipID = action.getShipID();
//        int containerID = action.getContainerID();
//
//        Ship ship = ships.get(shipID);
//        Container container = findContainerInShips(ships, containerID);
//
//        if (container != null && ship != null && ship.unLoad(container)) {
//            // Add the container to the port
//            ports.get(ship.getCurrentPort().getID()).getContainers().add(container);
//        }
//    }
//
//    private static void sailToPort(Map<Integer, Port> ports, Map<Integer, Ship> ships, Action action) {
//        int shipID = action.getShipID();
//        int destinationPortID = action.getDestinationPortID();
//
//        Ship ship = ships.get(shipID);
//        Port destinationPort = ports.get(destinationPortID);
//
//        if (ship != null && destinationPort != null) {
//            ship.sailTo(destinationPort);
//        }
//    }
//
//    private static void refuelShip(Map<Integer, Ship> ships, Action action) {
//        int shipID = action.getShipID();
//        double newFuel = action.getNewFuel();
//
//        Ship ship = ships.get(shipID);
//
//        if (ship != null) {
//            ship.reFuel(newFuel);
//        }
//    }
//
//    private static Container findContainerInPorts(Map<Integer, Port> ports, int containerID) {
//        for (Port port : ports.values()) {
//            for (Container container : port.getContainers()) {
//                if (container.getID() == containerID) {
//                    return container;
//                }
//            }
//        }
//        return null;
//    }
//
//    private static Container findContainerInShips(Map<Integer, Ship> ships, int containerID) {
//        for (Ship ship : ships.values()) {
//            for (Container container : ship.getContainers()) {
//                if (container.getID() == containerID) {
//                    return container;
//                }
//            }
//        }
//        return null;
//    }
//
//    private static void printResult(Map<Integer, Port> ports, Map<Integer, Ship> ships) {
//        Map<String, Object> result = new HashMap<>();
//
//        for (Port port : ports.values()) {
//            Map<String, Object> portData = new HashMap<>();
//            portData.put("lat", port.getLatitude());
//            portData.put("lon", port.getLongitude());
//
//            Map<String, List<Integer>> containerIDs = new HashMap<>();
//            for (Container container : port.getContainers()) {
//                String containerType = getContainerType(container);
//                containerIDs.computeIfAbsent(containerType, k -> new ArrayList<>()).add(container.getID());
//            }
//            portData.put("basic_container", containerIDs.getOrDefault("BasicContainer", List.of()));
//            portData.put("heavy_container", containerIDs.getOrDefault("HeavyContainer", List.of()));
//            portData.put("refrigerated_container", containerIDs.getOrDefault("RefrigeratedContainer", List.of()));
//            portData.put("liquid_container", containerIDs.getOrDefault("LiquidContainer", List.of()));
//
//            Map<String, Object> shipData = new HashMap<>();
//            for (Ship ship : port.getCurrent()) {
//                shipData.put("fuel_left", ship.getFuel());
//
//                Map<String, List<Integer>> shipContainerIDs = new HashMap<>();
//                for (Container container : ship.getContainers()) {
//                    String containerType = getContainerType(container);
//                    shipContainerIDs.computeIfAbsent(containerType, k -> new ArrayList<>()).add(container.getID());
//                }
//
//                shipData.put("basic_container", shipContainerIDs.getOrDefault("BasicContainer", List.of()));
//                shipData.put("heavy_container", shipContainerIDs.getOrDefault("HeavyContainer", List.of()));
//                shipData.put("refrigerated_container", shipContainerIDs.getOrDefault("RefrigeratedContainer", List.of()));
//                shipData.put("liquid_container", shipContainerIDs.getOrDefault("LiquidContainer", List.of()));
//
//                portData.put("ship_" + ship.getID(), shipData);
//            }
//
//            result.put("Port " + port.getID(), portData);
//        }
//
//        // Print the result as JSON
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("output.json"), result);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static String getContainerType(Container container) {
//        if (container instanceof Container.BasicContainer) {
//            return "BasicContainer";
//        } else if (container instanceof Container.HeavyContainer) {
//            return "HeavyContainer";
//        } else if (container instanceof Container.RefrigeratedContainer) {
//            return "RefrigeratedContainer";
//        } else if (container instanceof Container.LiquidContainer) {
//            return "LiquidContainer";
//        } else {
//            return "UnknownContainerType";
//        }
//    }

    }
}


