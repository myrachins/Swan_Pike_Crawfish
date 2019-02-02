public class AppSettings {
    final static int SLEEP_LOW_BOUND = 1000;
    final static int SLEEP_UPPER_BOUND = 5000;
    final static int REPEAT_OUTPUT_TIME = 2;

    final static int SWAN_ANGLE = 60;
    final static int PIKE_ANGLE = 180;
    final static int CRAWFISH_ANGLE = 300;

    final static int COEF_LOW_BOUND = 1;
    final static int COEF_UPPER_BOUND = 10;

    final static int WORK_TIME = 25 * 1000;

    static boolean SHOW_EACH_MOVE;
    static double START_X;
    static double START_Y;

    static void stateArguments(String[] args) {
        if(args == null) {
            throw new IllegalArgumentException();
        }
        for(int i = 0; i < args.length; i++) {
            if(args[i].startsWith("-")) {
                switch (args[i]) {
                    case "-i": SHOW_EACH_MOVE = true; break;
                    // Here can be more options
                }
            }
            else {
                if(i + 1 == args.length) {
                    throw new IllegalArgumentException("Coordinates should be specified both or do not be at all");
                }
                START_X = Double.parseDouble(args[i]);
                START_Y = Double.parseDouble(args[++i]);
            }
        }
    }
}
