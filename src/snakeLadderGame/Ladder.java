package snakeLadderGame;

public class Ladder {

    private final int start;
    private final int end;

    public Ladder(int end, int start) {
        this.end = end;
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
