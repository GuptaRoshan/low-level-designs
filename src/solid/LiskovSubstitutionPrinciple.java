package solid;

public class LiskovSubstitutionPrinciple {

    /**
     * Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of its subclasses
     * without affecting the correctness of the program.
     * Here, Green is a base super class of Blue. When a new object of Blue is assigned in an object of Super class,
     * getColor() of Green will be replaced by getColor() of Blue according to inheritance rules of OOP. In this situation,
     * we are expecting “Green” color from Green class. but we are getting “Blue” color from Green color. So the design of class is violating the LSP.
     */

    /*


    class Green {
        public void getColor() {
            System.out.println("Green");
        }
    }

    class Blue extends Green {
        public void getColor() {
            System.out.println("Blue");
        }
    }


    public void main0() {
        // violate LSP because color of green object is blue
        Green green = new Blue();
        green.getColor();
        //output: Blue
    }

    public static void main(String[] args){
        new LiskovSubstitutionPrinciple().main0();
    }


     */

    /**
     * To fix the above issue, we can use interface instead of inheritance.
     * By using interface, we can achieve the same functionality without violating the LSP.
     */

    public interface IColor {
        void getColor();
    }

    public static class Green implements IColor {
        public void getColor() {
            System.out.println("Green");
        }
    }

    public static class Blue implements IColor {
        public void getColor() {
            System.out.println("Blue");
        }
    }

}
