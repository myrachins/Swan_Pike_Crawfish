public class Truck {
    private int x;
    private int y;

    public synchronized int getX() {
        return x;
    }
    public synchronized int getY() {
        return y;
    }
}
