package snakeLadderGame;

import java.util.Deque;

public class SnakeAndLadderGame {

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
