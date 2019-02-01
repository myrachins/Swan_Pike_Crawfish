public class AppSettings {
    final static int SLEEP_LOW_BOUND = 1000;
    final static int SLEEP_UPPER_BOUND = 5000;
    final static int REPEAT_OUTPUT_TIME = 2;

    final static int SWAN_ANGLE = 60;
    final static int PIKE_ANGLE = 180;
    final static int CRAWFISH_ANGLE = 300;

    final static int COEF_LOW_BOUND = 1;
    final static int COEF_UPPER_BOUND = 10;

    /**
     *
     * @param a: Low bound of random
     * @param b: Upper bound of random
     * @return Random value from specified interval
     */
    public static double randValue(double a, double b) {
        if(a > b) {
            throw new IllegalArgumentException();
        }

        return a + Math.random() * (b - a);
    }
}
