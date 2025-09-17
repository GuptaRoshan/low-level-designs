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
    private final List<Snake> snakes;
    private final List<Ladder> ladders;

    public Board() {
        // Initialize snakes
        this.snakes = List.of(new Snake(16, 6), new Snake(48, 26), new Snake(64, 60), new Snake(93, 73));
        // Initialize ladders
        this.ladders = List.of(new Ladder(1, 38), new Ladder(4, 14), new Ladder(9, 31), new Ladder(21, 42), new Ladder(28, 84), new Ladder(51, 67), new Ladder(80, 99));
    }

    public int getSnakeAndLadderPosition(int position) {
        for (Snake snake : snakes) {
            if (snake.start() == position) return snake.end();
        }

        for (Ladder ladder : ladders) {
            if (ladder.start() == position) return ladder.end();
        }

        return 0;
    }

}

record Ladder(int start, int end) { }
record Snake(int start, int end) { }


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


class SnakeAndLadderGame {

    private final Board board;
    private final Dice dice;
    private final Deque<Player> players;

    public SnakeAndLadderGame(Deque<Player> players) {
        this.players = players;
        this.board = new Board();
        this.dice = new Dice();
    }

    public void play() {
        while (true) {
            Player currentPlayer = players.pollFirst();
            if (currentPlayer == null) continue;

            int currentPosition = currentPlayer.getPosition();   // Get Current Position
            int diceRoll = dice.roll();  // Roll the dice and update the position
            int updatedPosition = currentPosition + diceRoll;
            int snakeAndLadderPosition = board.getSnakeAndLadderPosition(updatedPosition);   // Apply any snake/ladder transformations to the updated position
            updatedPosition = (snakeAndLadderPosition == 0) ? updatedPosition : snakeAndLadderPosition;
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

public class SnakeAndLadderGameService {
    public static void main(String[] args) {
        Deque<Player> playerList = new LinkedList<>(List.of(new Player("Jack"), new Player("Alice"), new Player("Sam"), new Player("Nick")));
        SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame(playerList);
        snakeAndLadderGame.play();
    }
}
