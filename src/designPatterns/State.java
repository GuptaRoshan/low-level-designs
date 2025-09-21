package designPatterns;

/**
 * TrafficSignalSystem.java
 * -------------------
 * A simple traffic signal system using State Pattern.
 * <p>
 * Patterns used:
 * - State pattern: states like Red, Green, Yellow
 * <p>
 * Features:
 * - Cycle between red → green → yellow → red
 * - Timer-based step simulation
 * <p>
 * To run:
 * javac TrafficSignalSystem.java
 * java TrafficSignalSystem
 */

// State interface
interface SignalState {
    void next(TrafficSignal signal);

    String name();
}

// Red state
class RedState implements SignalState {
    private static final RedState INST = new RedState();

    private RedState() {
    }

    static RedState instance() {
        return INST;
    }

    public void next(TrafficSignal signal) {
        System.out.println("Switching from RED to GREEN");
        signal.changeState(GreenState.instance());
    }

    public String name() {
        return "Red";
    }
}

//------------------

// Green state
class GreenState implements SignalState {
    private static final GreenState INST = new GreenState();

    private GreenState() {
    }

    static GreenState instance() {
        return INST;
    }

    public void next(TrafficSignal signal) {
        System.out.println("Switching from GREEN to YELLOW");
        signal.changeState(YellowState.instance());
    }

    public String name() {
        return "Green";
    }
}

//------------------

// Yellow state
class YellowState implements SignalState {
    private static final YellowState INST = new YellowState();

    private YellowState() {
    }

    static YellowState instance() {
        return INST;
    }

    public void next(TrafficSignal signal) {
        System.out.println("Switching from YELLOW to RED");
        signal.changeState(RedState.instance());
    }

    public String name() {
        return "Yellow";
    }
}


class TrafficSignal {
    private SignalState state = RedState.instance();

    public void changeState(SignalState newState) {
        System.out.printf("State change: %s -> %s\n", state.name(), newState.name());
        this.state = newState;
    }

    public void step() {
        state.next(this);
    }

    public void printStatus() {
        System.out.printf("Current Signal: %s\n", state.name());
    }


}

class state {
    public static void main(String[] args) throws InterruptedException {
        TrafficSignal signal = new TrafficSignal();

        for (int i = 0; i < 6; i++) {
            signal.printStatus();
            Thread.sleep(1000);
            signal.step();
        }
    }
}
