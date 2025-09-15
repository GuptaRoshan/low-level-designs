package relationship;


import java.util.ArrayList;
import java.util.List;


// Tight Coupling, Room cannot exist without House
class Room {
    private String name;

    public Room(String name) {
        this.name = name;
    }
}

// When the House is destroyed, all rooms are destroyed
// Strong Association, House is responsible for creating and destroying rooms
class House {
    private List<Room> rooms; // Composition

    public House() {
        this.rooms = new ArrayList<>();
        rooms.add(new Room("Living Room"));
        rooms.add(new Room("Bedroom"));
        rooms.add(new Room("Kitchen"));
    }

    public List<Room> getRooms() {
        return rooms;
    }
}

public class Composition {
}
