package designPatterns;


// Abstract Factory
interface GUIFactory {
    Button createButton();
    TextBox createTextBox();
}

// Abstract Product: Button
interface Button {
    void render();
}

// Abstract Product: TextBox
interface TextBox {
    void render();
}

// Concrete Factory for Windows
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public TextBox createTextBox() {
        return new WindowsTextBox();
    }
}

// Concrete Factory for Mac
class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public TextBox createTextBox() {
        return new MacTextBox();
    }
}

// Concrete Product: Windows Button
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}

// Concrete Product: Windows TextBox
class WindowsTextBox implements TextBox {
    @Override
    public void render() {
        System.out.println("Rendering Windows TextBox");
    }
}

// Concrete Product: Mac Button
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Mac Button");
    }
}

// Concrete Product: Mac TextBox
class MacTextBox implements TextBox {
    @Override
    public void render() {
        System.out.println("Rendering Mac TextBox");
    }
}


public class AbstractFactory {

    public static void main(String[] args) {

        // Create a factory for Windows UI components
        GUIFactory windowsFactory = new WindowsFactory();
        Button windowsButton = windowsFactory.createButton();
        TextBox windowsTextBox = windowsFactory.createTextBox();

        windowsButton.render();   // Output: Rendering Windows Button
        windowsTextBox.render();  // Output: Rendering Windows TextBox

        // Create a factory for Mac UI components
        GUIFactory macFactory = new MacFactory();
        Button macButton = macFactory.createButton();
        TextBox macTextBox = macFactory.createTextBox();

        macButton.render();   // Output: Rendering Mac Button
        macTextBox.render();  // Output: Rendering Mac TextBox
    }

}
