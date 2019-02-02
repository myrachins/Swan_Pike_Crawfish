import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        try {
            AppSettings.stateArguments(args);
        } catch (IllegalArgumentException e) {
            System.out.println("Program can not continue. Please, see error message:");
            System.out.println(e.getMessage());
            return;
        }

        // Main truck to move
        Truck truck = new Truck(AppSettings.START_X, AppSettings.START_Y);

        System.out.println("Truck's start location: \t " + truck.getCoordinates() + System.lineSeparator());

        // Creating list of creatures
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(new Creature("Swan", AppSettings.SWAN_ANGLE));
        creatures.add(new Creature("Pike", AppSettings.PIKE_ANGLE));
        creatures.add(new Creature("Crawfish", AppSettings.CRAWFISH_ANGLE));

        // Starting timer to output message every specified seconds
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> System.out.println("Truck's location: \t " + truck.getCoordinates()),
                0, AppSettings.REPEAT_OUTPUT_TIME, TimeUnit.SECONDS);

        // Creating list of threads to execute
        ArrayList<Thread> threads = new ArrayList<>();

        for(Creature creature : creatures) {
            threads.add(new Thread(() -> {
                while (true) {
                    creature.moveTruck(truck);
                    if(AppSettings.SHOW_EACH_MOVE) {
                        System.out.printf("Truck is moved by %s to location: \t " + truck.getCoordinates() + System.lineSeparator(),
                                creature.getName());
                    }
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(AppSettings.SLEEP_LOW_BOUND,
                                AppSettings.SLEEP_UPPER_BOUND));
                    } catch (InterruptedException e) {
                        System.out.println("- " + creature.getName() + " finished 'work'");
                        break; // after interrupting going out from cycle
                    }
                }
            }));
        }

        // We start executing threads only here to make competition more fair - we do not spend much time on creation new
        // objects of streams, while already created objects are executing at the moment
        threads.forEach(Thread::start);
        Thread.sleep(AppSettings.WORK_TIME);

        // Interrupting all threads
        threads.forEach(Thread::interrupt);
        exec.shutdown();

        // Outputting all result information
        System.out.println(System.lineSeparator() + "Final location of truck: \t " + truck.getCoordinates());
        System.out.printf(System.lineSeparator() + "Time spent on 'work': \t %d (milliseconds)" + System.lineSeparator(),
                AppSettings.WORK_TIME);
    }
}
