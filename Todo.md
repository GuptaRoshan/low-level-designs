The Adapter pattern allows your code to integrate with new classes that have different interfaces without having to modify your core logic. This promotes code reusability and adherence to the **Open/Closed Principle**—your system is open for extension but closed for modification.

---

The **Command pattern** in Java is a behavioral design pattern that encapsulates a request as an object, thereby allowing you to parameterize clients with different requests, queue or log requests, and support undoable operations. It decouples the object that invokes the operation from the object that knows how to perform it.

### **Core Components**

1.  **Command (`Command`)**: An interface that declares a method for executing a request, typically named `execute()`.
2.  **Concrete Command (`LightOnCommand`, `LightOffCommand`)**: Classes that implement the `Command` interface. They encapsulate a request for a specific receiver by binding together an action and a receiver.
3.  **Receiver (`Light`)**: The object that performs the actual work. The `ConcreteCommand` holds a reference to a `Receiver` and invokes its specific actions.
4.  **Invoker (`RemoteControl`)**: The object that asks the command to carry out the request. It holds a reference to a `Command` object but doesn't know about the `ConcreteCommand` or `Receiver`. It simply calls the `execute()` method.
5.  **Client (`RemoteControlTest`)**: The object that creates a `ConcreteCommand` object and sets its receiver. It then hands the command to the `Invoker`.

-----

### **Example in Java**

Imagine a simple remote control for a light. The remote is the invoker, and the light is the receiver.

#### **1. The Command Interface**

```java
public interface Command {
    void execute();
}
```

#### **2. The Receiver**

The `Light` class is the receiver. It knows how to turn itself on or off.

```java
public class Light {
    public void on() {
        System.out.println("Light is On");
    }

    public void off() {
        System.out.println("Light is Off");
    }
}
```

#### **3. The Concrete Commands**

These classes encapsulate the request to turn the light on or off. They hold a reference to the `Light` receiver.

```java
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }
}
```

#### **4. The Invoker**

The `RemoteControl` is the invoker. It stores a command object and can trigger its execution. It doesn't know what the command does—only that it can be executed.

```java
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
```

#### **5. The Client**

The `main` method acts as the client. It sets up the receiver and concrete commands and then configures the invoker.

```java
public class RemoteControlTest {
    public static void main(String[] args) {
        // The Receiver
        Light livingRoomLight = new Light();

        // Concrete Commands
        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        // The Invoker
        RemoteControl remote = new RemoteControl();

        // Press the "On" button
        remote.setCommand(lightOn);
        remote.pressButton(); // Output: Light is On

        // Press the "Off" button
        remote.setCommand(lightOff);
        remote.pressButton(); // Output: Light is Off
    }
}
```

### **Benefits of the Command Pattern**

* **Decoupling**: The invoker is completely separated from the receiver. It only knows about the `Command` interface, making the system flexible.
* **Extensibility**: You can add new commands without changing the invoker. For instance, you could add `StereoOnCommand` without touching the `RemoteControl` class.
* **Support for Undo/Redo**: By adding an `unexecute()` method to the `Command` interface, you can store a history of commands to easily implement undo functionality.
* **Logging and Queueing**: Commands can be stored in a queue for delayed execution or logged for auditing purposes.


  ---

The **Mediator pattern** is a behavioral design pattern that reduces the dependencies between a set of communicating objects by having them communicate indirectly through a central object called the **mediator**. This pattern promotes loose coupling by keeping objects from referring to each other explicitly.

### **Core Components**

1.  **Mediator (`ChatMediator`)**: An interface that defines the communication methods between colleague objects.
2.  **Concrete Mediator (`ChatRoom`)**: The class that implements the `Mediator` interface. It manages and coordinates all the communication between the colleague objects.
3.  **Colleague (`User`)**: The objects that communicate with each other. Instead of sending messages directly, they send them to the mediator.
4.  **Concrete Colleague (`ChatUser`)**: The specific implementations of the colleague objects. They know about their mediator but not about other colleagues.

-----

### **Example in Java**

Imagine a chat room where users can send messages to each other. Without a mediator, each user would need a reference to every other user to send a message directly. This creates a complex web of dependencies. The Mediator pattern solves this by introducing a central `ChatRoom` that handles all message routing.

#### **1. The Mediator Interface**

```java
public interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}
```

#### **2. The Colleague Interface (User)**

```java
public abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}
```

#### **3. The Concrete Mediator**

The `ChatRoom` manages all the `User` objects and routes messages. It holds a list of all participants.

```java
import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : this.users) {
            // Message should not be sent back to the sender
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}
```

#### **4. The Concrete Colleague**

The `ChatUser` class sends messages to the mediator, not to other users directly.

```java
public class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(this.name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(this.name + " receives: " + message);
    }
}
```

#### **5. The Client**

The client sets up the mediator and the users, then initiates the communication.

```java
public class ChatRoomTest {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User john = new ChatUser(chatRoom, "John");
        User doe = new ChatUser(chatRoom, "Doe");
        User jane = new ChatUser(chatRoom, "Jane");

        chatRoom.addUser(john);
        chatRoom.addUser(doe);
        chatRoom.addUser(jane);

        john.send("Hi everyone!");
    }
}
```

**Output:**

```
John sends: Hi everyone!
Doe receives: Hi everyone!
Jane receives: Hi everyone!
```

### **Benefits of the Mediator Pattern**

* **Loose Coupling**: Components don't depend on each other. The mediator encapsulates how objects interact, so they don't need to know about each other's implementation details.
* **Centralized Control**: The mediator centralizes control logic. This makes it easier to modify the interaction rules without changing individual colleague classes.
* **Increased Reusability**: Because colleagues are not dependent on each other, they are more reusable. You can easily add or remove new colleagues without affecting existing ones.



  ---


The **Adapter pattern** is a structural design pattern that allows two incompatible interfaces to work together. It acts as a bridge between two classes by converting the interface of one class into an interface that the client expects. This pattern is particularly useful when you want to use an existing class in a new system that has a different interface, without modifying the source code of the existing class.

### **Core Components**

1.  **Target (`MediaPlayer`)**: The interface that the client wants to use.
2.  **Adaptee (`AdvanceMediaPlayer`)**: The existing class with an incompatible interface that needs to be adapted.
3.  **Adapter (`MediaAdapter`)**: The class that implements the **Target** interface and holds a reference to the **Adaptee** object. It translates the requests from the client to the **Adaptee**.
4.  **Client (`AudioPlayer`)**: The object that interacts with the **Target** interface. It is unaware of the **Adaptee** and the adapter's implementation.

-----

### **Example in Java**

Imagine you have an audio player that can only play MP3 files. You want to add support for playing more advanced formats like MP4 and VLC. You can use the Adapter pattern to achieve this without changing the `AudioPlayer` class.

#### **1. The Target Interface**

This is the interface the client (the `AudioPlayer`) expects.

```java
public interface MediaPlayer {
    void play(String audioType, String fileName);
}
```

#### **2. The Adaptee Interface and its Concrete Classes**

This is the existing, incompatible interface.

```java
public interface AdvanceMediaPlayer {
    void playVlc(String fileName);
    void playMp4(String fileName);
}

public class VlcPlayer implements AdvanceMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing vlc file. Name: " + fileName);
    }
    @Override
    public void playMp4(String fileName) {
        // Do nothing
    }
}

public class Mp4Player implements AdvanceMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Do nothing
    }
    @Override
    public void playMp4(String fileName) {
        System.out.println("Playing mp4 file. Name: " + fileName);
    }
}
```

#### **3. The Adapter Class**

The `MediaAdapter` implements the `MediaPlayer` (Target) interface and adapts the `AdvanceMediaPlayer` (Adaptee) to it.

```java
public class MediaAdapter implements MediaPlayer {
    AdvanceMediaPlayer advanceMusicPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advanceMusicPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advanceMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advanceMusicPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advanceMusicPlayer.playMp4(fileName);
        }
    }
}
```

#### **4. The Client**

The `AudioPlayer` is the client. It only knows about the `MediaPlayer` interface and can now play new formats through the adapter.

```java
public class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported.");
        }
    }
}
```

#### **5. The Demonstration**

```java
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond_the_horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("vlc", "far_far_away.vlc");
        audioPlayer.play("avi", "mind_control.avi");
    }
}
```

**Output:**

```
Playing mp3 file. Name: beyond_the_horizon.mp3
Playing mp4 file. Name: alone.mp4
Playing vlc file. Name: far_far_away.vlc
Invalid media. avi format not supported.
```

### **Key Takeaway**

