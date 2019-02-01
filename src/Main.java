import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Truck truck = new Truck();

        // Creating list of creatures
        ArrayList<Creature> creatures = new ArrayList<>();
        creatures.add(new Creature("Swan", AppSettings.SWAN_ANGLE));
        creatures.add(new Creature("Pike", AppSettings.PIKE_ANGLE));
        creatures.add(new Creature("Crawfish", AppSettings.CRAWFISH_ANGLE));

        // Starting timer to output message every specified seconds
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(() -> System.out.printf("Truck location: (%f, %f)" + System.lineSeparator(),
                truck.getX(), truck.getY()), 0, AppSettings.REPEAT_OUTPUT_TIME, TimeUnit.SECONDS);

        // Creating list of threads to execute
        ArrayList<Thread> threads = new ArrayList<>();

        for(Creature creature : creatures) {
            Thread thread = new Thread(() -> {
                while (true) {
                    creature.moveTruck(truck);
                    try {
                        Thread.sleep((int) AppSettings.randValue(AppSettings.SLEEP_LOW_BOUND, AppSettings.SLEEP_UPPER_BOUND));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            });
            threads.add(thread);
            thread.start();
        }

        // Interrupt all threads after 25 seconds
//        for(Thread thread : threads) {
//            thread.interrupt();
//        }
    }
}
