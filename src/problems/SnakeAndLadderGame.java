package problems;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

class Dice {

    private static final int size = 6;
    private final Random random;

    public Dice() {
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(size) + 1;
    }

}

class Board {

    public static final int BOARD_SIZE = 100;
    private List<Snake> snakes;
    private List<Ladder> ladders;

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
                new Ladder(80, 99));
    }

    public int getSnakeAndLadderPosition(int position) {
        for (Snake snake : snakes) {
            if (snake.getStart() == position)
                return snake.getEnd();
        }

        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position)
                return ladder.getEnd();
        }

        return 0;
    }

}

class Ladder {

    private final int start;
    private final int end;

    public Ladder(int end, int start) {
        this.end = end;
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}


class Player {
    private final String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 1;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }
}

class Snake {

    private final int start;
    private final int end;

    public Snake(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

class SnakeAndLadderGameService {

    private final Board board;
    private final Dice dice;
    private final Deque<Player> players;

    public SnakeAndLadderGameService(Deque<Player> players) {
        this.players = players;
        this.board = new Board();
        this.dice = new Dice();
    }

    public void play() {

        while (true) {
            Player currentPlayer = players.pollFirst();
            if (currentPlayer == null) continue;

            // Get Current Position
            int currentPosition = currentPlayer.getPosition();
            // Roll the dice and update the position
            int diceRoll = dice.roll();
            int updatedPosition = currentPosition + diceRoll;
            // Apply any snake/ladder transformations to the updated position
            int snakeAndLadderPosition = board.getSnakeAndLadderPosition(updatedPosition);
            updatedPosition = (snakeAndLadderPosition == 0) ? updatedPosition : snakeAndLadderPosition;
            // Update the player's position
            currentPlayer.setPosition(updatedPosition);

            // Check if the player has won
            if (updatedPosition >= Board.BOARD_SIZE) {
                System.out.printf("Player %s won the game with position %d!%n", currentPlayer.getName(), updatedPosition);
                break;
            }
            // Move the player to the back of the queue for the next round
            players.addLast(currentPlayer);
        }
    }

}

public class SnakeAndLadderGame {
    public static void main(String[] args) {
        Deque<Player> playerList = new LinkedList<>(List.of(new Player("Jack"), new Player("Alice"), new Player("Sam"), new Player("Nick")));

        SnakeAndLadderGameService s = new SnakeAndLadderGameService(playerList);
        s.play();
    }
}
