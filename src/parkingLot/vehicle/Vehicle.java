package parkingLot.vehicle;

public abstract class Vehicle {

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
