package designPatterns.behavioral;


import java.util.ArrayList;
import java.util.List;


interface Observer {
    void update(String message);
}

class ConcreteObserver implements Observer {
    private final String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}


class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}


public class PubSub {

    public static void main(String[] args) {
        Subject subject = new Subject();
        subject.addObserver(new ConcreteObserver("Observer 1"));
        subject.addObserver(new ConcreteObserver("Observer 2"));
        subject.notifyObservers("New event!");
    }

}
