package snakeLadderGame;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SnakeAndLadderGameDemo {

    public static void main(String[] args) {
        Deque<Player> playerList = new LinkedList<>(List.of(
                new Player("Jack"),
                new Player("Alice"),
                new Player("Sam"),
                new Player("Nick")));

        SnakeAndLadderGame snakeAndLadderGame = new SnakeAndLadderGame(playerList);
        snakeAndLadderGame.play();
    }

}
