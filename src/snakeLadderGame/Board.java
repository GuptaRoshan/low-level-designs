package snakeLadderGame;

import java.util.List;

public class Board {

    public static final int BOARD_SIZE = 100;
    private  List<Snake> snakes;
    private  List<Ladder> ladders;

    public Board() {
        initSnakesAndLadders();
    }

    public void initSnakesAndLadders() {
        // Initialize snakes
        this.snakes = List.of(new Snake(16, 6),
                new Snake(48, 26),
                new Snake(64, 60),
                new Snake(93, 73));

        // Initialize ladders
        this.ladders = List.of(
                new Ladder(1, 38),
                new Ladder(4, 14),
                new Ladder(9, 31),
                new Ladder(21, 42),
                new Ladder(28, 84),
                new Ladder(51, 67),
                new Ladder(80, 99)
        );
    }

    public int getSnakeAndLadderPosition(int position) {
        for (Snake snake : snakes) {
            if (snake.getStart() == position) return snake.getEnd();
        }

        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position) return ladder.getEnd();
        }

        return 0;
    }

}


