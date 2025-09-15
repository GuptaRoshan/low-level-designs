package problems.parkingLot;


import java.util.ArrayList;
import java.util.List;

import problems.parkingLot.vehicle.Vehicle;

public class ParkingLot {
    private static ParkingLot instance;
    List<Level> levels;

    private ParkingLot() {
        this.levels = new ArrayList<>();
    }

    public synchronized static ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        boolean isParked = false;
        for (Level level : levels) {
            isParked = level.parkVehicle(vehicle);
        }
        return isParked;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        boolean isUnParked = false;
        for (Level level : levels) {
            isUnParked = level.unParkVehicle(vehicle);
        }

        return isUnParked;
    }

    public void displayAvailability() {
        for (Level level : levels) {
            level.displayAvailability();
        }
    }


}
