package designPatterns;

/*
 * State Design Pattern
 *
 * Definition:
 * The State Pattern allows an object to change its behavior when its internal state changes.
 * It encapsulates state-specific behavior into separate classes.
 *
 * Real-World Analogy:
 * Traffic Signal: A traffic light changes its behavior based on its current state (Green, Yellow, or Red).
 * Each state determines what vehicles and pedestrians should do.
 */

// State interface
interface TrafficLightState {
    void changeColor();
}

// Concrete State classes
class RedState implements TrafficLightState {
    @Override
    public void changeColor() {
        System.out.println("Stop! Red Light");
    }
}

class YellowState implements TrafficLightState {
    @Override
    public void changeColor() {
        System.out.println("Prepare to stop. Yellow Light");
    }
}

class GreenState implements TrafficLightState {
    @Override
    public void changeColor() {
        System.out.println("Go! Green Light");
    }
}

// Context class
class TrafficLight {
    private TrafficLightState currentState;

    public TrafficLight() {
        // Start with red state
        currentState = new RedState();
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void changeColor() {
        currentState.changeColor();
    }
}


public class State {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();
        trafficLight.changeColor();
        trafficLight.setState(new YellowState());
        trafficLight.changeColor();
    }
}