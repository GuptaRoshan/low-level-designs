package designPatterns;


import java.util.HashMap;
import java.util.Map;

// Goal of Flyweight

// Reduce memory usage when you have a large number of objects that share common, reusable data.
// Store shared ("intrinsic") state in a central place.
// Pass in unique ("extrinsic") state when needed.

interface Character {
    void display(String fontSize, String color);  // extrinsic state
}

// Concrete Flyweight
class CharacterA implements Character {
    private final String symbol = "A"; // intrinsic state

    @Override
    public void display(String fontSize, String color) {
        System.out.println("Character: " + symbol + ", Font Size: " + fontSize + ", Color: " + color);
    }
}

class CharacterB implements Character {
    private final String symbol = "B";

    @Override
    public void display(String fontSize, String color) {
        System.out.println("Character: " + symbol + ", Font Size: " + fontSize + ", Color: " + color);
    }
}

// Flyweight Factory
class CharacterFactory {
    private static final Map<String, Character> characters = new HashMap<>();

    public static Character getCharacter(String key) {
        if (!characters.containsKey(key)) {
            switch (key) {
                case "A" -> characters.put(key, new CharacterA());
                case "B" -> characters.put(key, new CharacterB());
                // more cases
            }
        }
        return characters.get(key);
    }
}

public class Flyweight {

    public static void main(String[] args) {
        Character c1 = CharacterFactory.getCharacter("A");
        c1.display("12pt", "red");

        Character c2 = CharacterFactory.getCharacter("A");
        c2.display("14pt", "blue");

        Character c3 = CharacterFactory.getCharacter("B");
        c3.display("12pt", "black");

        // c1 and c2 are the same object (shared)
        System.out.println("Is c1 same as c2? " + (c1 == c2));  // true
    }
}


