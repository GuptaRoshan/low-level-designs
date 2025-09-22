package designPatterns.creational;

@SuppressWarnings("all")
class House {
    private final String foundation;
    private final String structure;
    private final String roof;

    private House(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.structure = builder.structure;
        this.roof = builder.roof;
    }

    public static class HouseBuilder {
        private String foundation;
        private String structure;
        private String roof;

        public HouseBuilder setFoundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public HouseBuilder setStructure(String structure) {
            this.structure = structure;
            return this;
        }

        public HouseBuilder setRoof(String roof) {
            this.roof = roof;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}


public class Builder {

    public static void main(String[] args) {
        House house = new House.HouseBuilder().setFoundation("Concrete").setStructure("Wood").setRoof("Tiles").build();
    }

}
