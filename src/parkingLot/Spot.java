package parkingLot;


import parkingLot.vehicle.Vehicle;
import parkingLot.vehicle.VehicleType;

public class Spot {

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
