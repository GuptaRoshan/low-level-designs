package solid;

public class OpenClosedPrinciple {
    /**
     * The Open/Closed Principle (OCP) states that classes should be open for extension but closed for modification.
     * <p>
     * Now, let’s say we want to calculate the area of another shape.
     * Now, we need to directly modify this class to add this feature and we would violate the OCP.
     */
    public class AreaCalculator {
        public double calculateRectangleArea(double width, double height) {
            return width * height;
        }

        public double calculateCircleArea(double radius) {
            return Math.PI * radius * radius;
        }

        // New method added for calculating the area of a triangle
        public double calculateTriangleArea(double base, double height) {
            return 0.5 * base * height;
        }
    }

    // ---------------------------------------------------------------------------------------------------------------------//


    /**
     * To follow the Open/Closed Principle, we can create an interface Shape and implement it for each shape.
     * Each shape class will have its own implementation of the calculateArea() method.
     * <p>
     * The AreaCalculator class will have a method that accepts the Shape interface as a parameter.
     * This method will calculate the area of any shape that implements the Shape interface.
     */

    // Interface representing a shape
    public interface Shape {
        // implement it for each shape
        double calculateArea();
    }


    // Rectangle implementation
    public class Rectangle implements Shape {
        private final double width;
        private final double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return width * height;
        }
    }


    // Circle implementation
    public class Circle implements Shape {
        private final double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }


    // Triangle implementation
    public class Triangle implements Shape {
        private final double base;
        private final double height;

        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }

        @Override
        public double calculateArea() {
            return 0.5 * base * height;
        }
    }

}
