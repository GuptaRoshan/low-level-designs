package solid;

public class InterfaceSegregationPrinciple {

    /**
     * In this example, a worker is expected to work and eat.
     * However, if a worker is a robot, then it cannot eat
     */
    public interface Worker {
        void work();

        void eat();
    }


    /**
     * Applying Interface Segregation Principle
     */
    // Interface representing a worker with work action
    public interface Workable {
        void work();
    }

    // Interface representing an eater
    public interface Eatable {
        void eat();
    }

    // Robot implementing only the Workable interface
    public class Robot implements Workable {
        @Override
        public void work() {
            System.out.println("Robot working");
        }
    }

    // Human implementing both Workable and Eatable interfaces
    public class Human implements Workable, Eatable {
        @Override
        public void work() {
            System.out.println("Human working");
        }

        @Override
        public void eat() {
            System.out.println("Human eating");
        }
    }

}
