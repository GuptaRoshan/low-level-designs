package designPatterns.creational;


interface Animal {
    void speak();
}


class Dog implements Animal {
    public void speak() {
        System.out.println("Woof");
    }
}


class Cat implements Animal {
    public void speak() {
        System.out.println("Meow");
    }
}


class AnimalFactory {
    public Animal createAnimal(String type) {
        return switch (type) {
            case "Dog" -> new Dog();
            case "Cat" -> new Cat();
            default -> null;
        };
    }
}


public class Factory {
    public static void main(String[] args) {
        AnimalFactory animalFactory = new AnimalFactory();
        Animal dog = animalFactory.createAnimal("Dog");
        dog.speak();
    }
}
