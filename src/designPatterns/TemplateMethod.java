package designPatterns;


/**
 * Key Concepts
 * <p>
 * Template Method:
 * - A method in a superclass that outlines the steps of an algorithm.
 * - Some steps are implemented in the superclass, while others are declared
 * as abstract and implemented in subclasses.
 * <p>
 * Hook Methods:
 * - Optional methods in the superclass that can be overridden in subclasses
 * to extend or modify behavior.
 * <p>
 * Inversion of Control:
 * - The pattern dictates the structure of the algorithm, ensuring subclasses
 * adhere to it.
 * <p>
 * Real-World Example: Coffee and Tea Preparation
 * - Consider a system for preparing beverages. The steps for making coffee
 * and tea are similar:
 * 1. Boil water.
 * 2. Brew the drink (specific to coffee or tea).
 * 3. Pour into a cup.
 * 4. Add condiments (like milk or sugar, specific to coffee or tea).
 * - The Template Method captures the shared structure, while subclasses handle
 * the specifics.
 */
abstract class Beverage {

    // Template Method
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    private void boilWater() {
        System.out.println("Boiling water");
    }

    private void pourInCup() {
        System.out.println("Pouring into the cup");
    }


    // Abstract methods for subclass-specific behavior
    protected abstract void brew();

    protected abstract void addCondiments();
}


class Tea extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding lemon");
    }
}


class Coffees extends Beverage {
    @Override
    protected void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    protected void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}


public class TemplateMethod {

    public static void main(String[] args) {
        System.out.println("Preparing Tea:");
        Beverage tea = new Tea();
        tea.prepareRecipe();

        System.out.println("\nPreparing Coffee:");
        Beverage coffee = new Coffees();
        coffee.prepareRecipe();
    }
}
