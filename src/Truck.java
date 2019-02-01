public class Truck {
    private double x;
    private double y;

    public synchronized double getX() {
        return x;
    }

    public synchronized void setX(double x) {
        this.x = x;
    }

    public synchronized double getY() {
        return y;
    }

    public synchronized void setY(double y) {
        this.y = y;
    }
}
