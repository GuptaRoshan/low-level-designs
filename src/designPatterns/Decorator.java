package designPatterns;


// Pizza interface representing the core pizza functionality
interface Pizza {
    String getDescription(); // Describes the pizza

    double getCost(); // Returns the cost of the pizza
}

// SimplePizza class implementing the basic Pizza interface
class SimplePizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain pizza";
    }

    @Override
    public double getCost() {
        return 10.0;
    }
}

// Abstract PizzaDecorator class implementing the Pizza interface
abstract class PizzaDecorator implements Pizza {
    protected Pizza pizza; // Holds the pizza object to decorate

    public PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription(); // Delegates to the decorated pizza
    }

    @Override
    public double getCost() {
        return pizza.getCost(); // Delegates to the decorated pizza
    }
}

// Topping decorators
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with extra cheese";
    }

    @Override
    public double getCost() {
        return super.getCost() + 2.0;
    }
}

class OliveDecorator extends PizzaDecorator {
    public OliveDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with olives";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.5;
    }
}



public class Decorator {
    public static void main(String[] args) {
        Pizza pizza = new SimplePizza(); // Start with a plain pizza
        pizza = new CheeseDecorator(pizza); // Add cheese
        pizza = new OliveDecorator(pizza); // Add olives

        // Outputs: Plain pizza, with extra cheese, with olives
        System.out.println(pizza.getDescription());
        // Outputs: 13.5 (10 + 2 + 1.5)
        System.out.println("Total Cost: " + pizza.getCost());
    }
}
