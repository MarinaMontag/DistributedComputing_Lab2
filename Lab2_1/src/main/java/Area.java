public class Area {
    private final int number;
    private final boolean winnie;

    Area(int number, boolean winnie){
        this.number=number;
        this.winnie=winnie;
    }

    public boolean isWinnie() {
        return winnie;
    }

    public int getNumber() {
        return number;
    }
}
