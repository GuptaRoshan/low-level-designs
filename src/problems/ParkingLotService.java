package problems;


import java.util.ArrayList;
import java.util.List;

enum VehicleType {
    CAR, TRUCK, MOTORCYCLE
}

abstract class Vehicle {

    private final String licensePlate;
    private final String vehicleId;
    private final VehicleType vehicleType;

    public Vehicle(String licencePlate, String vehicleId, VehicleType vehicleType) {
        this.licensePlate = licencePlate;
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "licensePlate='" + licensePlate + '\'' + ", vehicleId='" + vehicleId + '\'' + ", vehicleType=" + vehicleType + '}';
    }
}


class Car extends Vehicle {

    public Car(String licencePlate, String vehicleId, VehicleType vehicleType) {
        super(licencePlate, vehicleId, vehicleType);
    }
}


class Motorcycle extends Vehicle {
    public Motorcycle(String licencePlate, String vehicleId, VehicleType vehicleType) {
        super(licencePlate, vehicleId, vehicleType);
    }
}


class Truck extends Vehicle {

    public Truck(String licencePlate, String vehicleId, VehicleType vehicleType) {
        super(licencePlate, vehicleId, vehicleType);
    }
}


class Level {

    private final int floor;
    private final List<Spot> level;

    public Level(int floor, int numberOfSpots) {
        this.floor = floor;
        this.level = new ArrayList<>(numberOfSpots);

        int numberOfCars = (int) (numberOfSpots * 0.4);
        int numberOfTruck = (int) (numberOfSpots * 0.4);
        int numberOfMotorcycle = (int) (numberOfSpots * 0.2);

        int spotId = 1;

        for (; spotId <= numberOfCars; spotId++) {
            level.add(new Spot(spotId, VehicleType.CAR));
        }
        for (; spotId <= numberOfCars + numberOfTruck; spotId++) {
            level.add(new Spot(spotId, VehicleType.TRUCK));
        }
        for (; spotId <= numberOfCars + numberOfTruck + numberOfMotorcycle; spotId++) {
            level.add(new Spot(spotId, VehicleType.MOTORCYCLE));
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


class Spot {

    private final int spotNumber;
    private final VehicleType vehicleType;
    private boolean isAvailable;
    private Vehicle vehicle;

    public Spot(int spotNumber, VehicleType vehicleType) {
        this.spotNumber = spotNumber;
        this.isAvailable = true;
        this.vehicle = null;
        this.vehicleType = vehicleType;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isAvailable = false;
    }

    public void unParkVehicle() {
        this.vehicle = null;
        this.isAvailable = true;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}


class ParkingLot {
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


public class ParkingLotService {

    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new Level(1, 10));

        parkingLot.parkVehicle(new Car("DL102", "123", VehicleType.CAR));
        parkingLot.parkVehicle(new Truck("DL102", "123", VehicleType.TRUCK));
        parkingLot.parkVehicle(new Motorcycle("DL102", "123", VehicleType.MOTORCYCLE));
        parkingLot.parkVehicle(new Motorcycle("DL102", "123", VehicleType.MOTORCYCLE));

        Vehicle car = new Car("DL102", "123", VehicleType.CAR);
        parkingLot.parkVehicle(car);

        parkingLot.displayAvailability();

        parkingLot.unParkVehicle(car);

        System.out.println("\n");

        parkingLot.displayAvailability();
    }
}
