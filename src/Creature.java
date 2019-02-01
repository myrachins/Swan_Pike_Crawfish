public class Creature {
    private String name;
    private double angle;

    /**
     * Cosine of angle value. Calculates just ones
     */
    private double cosAngle;
    /**
     * Sinus of angle value. Calculates just ones
     */
    private double sinAngle;

    /**
     *
     * @param name: Name of creature
     * @param angle: Angle of creature in degrees
     */
    public Creature(String name, int angle) {
        this.name = name;
        this.angle = angle;
        this.cosAngle = Math.cos(Math.PI * angle / 180f);
        this.sinAngle = Math.sin(Math.PI * angle / 180f);
    }

    public String getName() {
        return name;
    }

    public double getAngle() {
        return angle;
    }

    public void moveTruck(Truck truck) {
        synchronized (truck) {
            double coef = AppSettings.randValue(AppSettings.COEF_LOW_BOUND, AppSettings.COEF_UPPER_BOUND);
            double x = truck.getX() + coef * cosAngle;
            double y = truck.getY() + coef * sinAngle;
            truck.setX(x);
            truck.setY(y);

            truck.notifyAll();
        }
    }
}
