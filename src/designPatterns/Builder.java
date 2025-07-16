package designPatterns;

@SuppressWarnings("all")
class House0 {
    private final String foundation;
    private final String structure;
    private final String roof;

    private House0(HouseBuilder builder) {
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

        public House0 build() {
            return new House0(this);
        }
    }
}


public class Builder {

    public static void main(String[] args) {
        House0 house = new House0.HouseBuilder().setFoundation("Concrete").setStructure("Wood").setRoof("Tiles").build();
    }

}
