package parkingLot;

import parkingLot.vehicle.*;

public class ParkingLotDemo {

    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addLevel(new Level(1, 10));

        parkingLot.parkVehicle(new Car("DL102", "123", VehicleType.CAR));
        parkingLot.parkVehicle(new Truck("DL102", "123", VehicleType.TRUCK));
        parkingLot.parkVehicle(new Motorcycle("DL102", "123", VehicleType.MOTORCYCLE));
        parkingLot.parkVehicle(new Motorcycle("DL102", "123", VehicleType.MOTORCYCLE));

        Vehicle car = new  Car("DL102", "123", VehicleType.CAR);
        parkingLot.parkVehicle(car);

        parkingLot.displayAvailability();

        parkingLot.unParkVehicle(car);

        System.out.println("\n");

        parkingLot.displayAvailability();
    }
}
