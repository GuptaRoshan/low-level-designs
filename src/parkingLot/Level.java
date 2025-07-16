package parkingLot;

import parkingLot.vehicle.Vehicle;
import parkingLot.vehicle.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private final int floor;
    private final List<Spot> level;

    public Level(int floor, int numberOfSpots) {
        this.floor = floor;
        this.level = new ArrayList<>(numberOfSpots);

        int numberOfCars = (int) (numberOfSpots * 0.4);
        int numberOfTruck = (int) (numberOfSpots * 0.4);
        int numberOfMotorcycle = (int) (numberOfSpots * 0.2);

        for (int i = 1; i <= numberOfCars; i++) {
            level.add(new Spot(i, VehicleType.CAR));
        }

        for (int i = numberOfCars + 1; i <= numberOfCars + numberOfTruck; i++) {
            level.add(new Spot(i, VehicleType.TRUCK));
        }

        for (int i = numberOfCars + numberOfTruck + 1; i <= numberOfCars + numberOfTruck + numberOfMotorcycle; i++) {
            level.add(new Spot(i, VehicleType.MOTORCYCLE));
        }

    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (Spot spot : level) {
            if (spot.isAvailable() && spot.getVehicleType() == vehicle.getVehicleType()) {
                spot.parkVehicle(vehicle);
                return true;
            }
        }

        return false;
    }

    public boolean unParkVehicle(Vehicle vehicle) {
        for (Spot spot : level) {
            if (spot.getVehicle() == vehicle) {
                spot.unParkVehicle();
                return true;
            }
        }

        return false;
    }

    public void displayAvailability() {
        for (Spot spot : level) {
            String available = spot.isAvailable() ? ", is available" : ", occupied by : " + spot.getVehicle().toString();
            System.out.println("Floor No. : " + floor + ", Spot No. : " + spot.getSpotNumber() + available);
        }
    }

}
