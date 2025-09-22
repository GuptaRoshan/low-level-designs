package designPatterns.behavioral;

// Base interface for all assets
interface Asset {
    double accept(AssetVisitor visitor);
}

// Different types of assets
class House implements Asset {
    private final double value;

    public House(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double accept(AssetVisitor visitor) {
        return visitor.visit(this);
    }
}

class Car implements Asset {
    private final double value;

    public Car(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double accept(AssetVisitor visitor) {
        return visitor.visit(this);
    }
}

// Generic visitor interface
interface AssetVisitor {
    double visit(House house);
    double visit(Car car);
}

// Concrete visitor for tax calculations
class TaxCalculationVisitor implements AssetVisitor {
    @Override
    public double visit(House house) {
        // Property tax: 1% of house value
        return house.getValue() * 0.01;
    }

    @Override
    public double visit(Car car) {
        // Vehicle tax: 2% of car value
        return car.getValue() * 0.02;
    }
}

// Concrete visitor for insurance calculations
class InsuranceCalculationVisitor implements AssetVisitor {
    @Override
    public double visit(House house) {
        // House insurance premium: 0.5% of value
        return house.getValue() * 0.005;
    }

    @Override
    public double visit(Car car) {
        // Car insurance premium: 3% of value
        return car.getValue() * 0.03;
    }
}

// Main class demonstrating the visitor pattern
public class Visitor {
    public static void main(String[] args) {
        // Create assets
        Asset house = new House(200000);  // $200,000 house
        Asset car = new Car(30000);       // $30,000 car

        // Create visitors
        AssetVisitor taxCalculator = new TaxCalculationVisitor();
        AssetVisitor insuranceCalculator = new InsuranceCalculationVisitor();

        // Calculate and display tax for each asset
        System.out.println("Tax Calculations:");
        System.out.printf("House Tax: $%.2f%n", house.accept(taxCalculator));
        System.out.printf("Car Tax: $%.2f%n", car.accept(taxCalculator));

        // Calculate and display insurance for each asset
        System.out.println("\nInsurance Calculations:");
        System.out.printf("House Insurance Premium: $%.2f%n", house.accept(insuranceCalculator));
        System.out.printf("Car Insurance Premium: $%.2f%n", car.accept(insuranceCalculator));
    }
}