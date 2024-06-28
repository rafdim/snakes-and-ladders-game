public class Ladder {
    private int fromPosition;
    private int toPosition;

    public Ladder(int fromPosition, int toPosition) {
        this.fromPosition = fromPosition;
        this.toPosition = toPosition;
    }

    public void setFromPosition(int fromPosition) {
        this.fromPosition = fromPosition;
    }

    public void setToPosition(int toPosition) {
        this.toPosition = toPosition;
    }

    public int getFromPosition() {
        return this.fromPosition;
    }
    public int getToPosition() {
        return this.toPosition;
    }
}