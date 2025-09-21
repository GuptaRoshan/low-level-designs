package designPatterns;


import java.util.ArrayList;
import java.util.List;


/*
 * Composite Design Pattern
 *
 * Definition:
 * The Composite Pattern allows you to compose objects into tree structures and work with them as if
 * they were individual objects.
 *
 * Real-World Analogy:
 * Company Hierarchy: An organization has a tree-like structure where managers can have subordinates,
 * and subordinates can also be managers for others.
 */


// Component
interface FileComponent {
    void display();
}


class File implements FileComponent {
    private final String name;

    public File(String name) {
        this.name = name;
    }

    public void display() {
        System.out.println("\tFile: " + name);
    }
}

class Directory implements FileComponent {
    private final String name;
    private final List<FileComponent> components;

    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void add(FileComponent component) {
        components.add(component);
    }

    public void remove(FileComponent component) {
        components.remove(component);
    }

    public void display() {
        System.out.println("Folder: " + name);

        for (FileComponent component : components) {
            component.display(); // If its a file it will print its name, if its directory it will make recursive call
        }
    }

}

// Usage
public class Composite {
    public static void main(String[] args) {
        Directory folder1 = new Directory("Folder1");
        folder1.add(new File("Document1.txt"));
        folder1.add(new File("Document2.txt"));
        folder1.add(new File("Document3.txt"));

        Directory folder2 = new Directory("Folder2");
        folder2.add(new File("Photo1.jpg"));
        folder2.add(new File("Photo2.jpg"));
        folder2.add(new File("Photo3.jpg"));

        folder1.add(folder2);

        folder1.display();
    }
}

