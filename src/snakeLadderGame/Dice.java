package snakeLadderGame;

import java.util.Random;

public class Dice {

    private static final Dice INSTANCE = new Dice();
    private static final int size = 6;
    private final Random random;

    public Dice() {
        this.random = new Random();
    }


    public int roll() {
        return random.nextInt(size) + 1;
    }

}
