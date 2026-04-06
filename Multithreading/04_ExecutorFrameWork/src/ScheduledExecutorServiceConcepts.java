import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceConcepts {

    public static void main(String[] args) {
        // THEORY: ScheduledExecutorService runs tasks in the future (once or repeatedly).
        // THEORY: Use single-thread scheduler for strict order, pool scheduler for multiple jobs.
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        try {
            oneTimeTaskWithResult(scheduler);
            periodicTaskExamples(scheduler);

            // Keep main alive long enough to observe periodic behavior.
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            shutdownGracefully(scheduler);
        }
    }
    private static void oneTimeTaskWithResult(ScheduledExecutorService scheduler) {
        // THEORY: schedule(Callable, delay, unit) returns ScheduledFuture<T>.
        ScheduledFuture<Integer> delayedAnswer = scheduler.schedule(() -> {
            System.out.println("[one-time] Executed after delay");
            return 42;
        }, 1, TimeUnit.SECONDS);

        try {
            Integer result = delayedAnswer.get();
            System.out.println("[one-time] Result = " + result);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.err.println("[one-time] Task failed: " + e.getCause());
        }
    }
//    should we use the schedule to use the .shutdown method

    private static void periodicTaskExamples(ScheduledExecutorService scheduler) {
        // THEORY: fixed-rate tries to maintain a strict cadence (clock-driven behavior).
        ScheduledFuture<?> fixedRateFuture = scheduler.scheduleAtFixedRate(() -> {
            System.out.println("[fixed-rate] tick at " + System.currentTimeMillis());
        }, 0, 1, TimeUnit.SECONDS);

        // THEORY: fixed-delay waits for task completion, then waits delay, then runs again.
        ScheduledFuture<?> fixedDelayFuture = scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("[fixed-delay] start " + System.currentTimeMillis());
            try {
                Thread.sleep(600); // Simulate variable/slow work.
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("[fixed-delay] end   " + System.currentTimeMillis());
        }, 0, 1, TimeUnit.SECONDS);

        // THEORY: Cancel periodic work explicitly when you no longer need it.
        scheduler.schedule(() -> {
            fixedRateFuture.cancel(false); // false = do not interrupt if currently running.
            fixedDelayFuture.cancel(false);
            System.out.println("[cancel] periodic tasks cancelled");
        }, 5, TimeUnit.SECONDS);
    }

    private static void shutdownGracefully(ScheduledExecutorService scheduler) {
        // THEORY: Always shutdown executors to avoid thread leaks.
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow(); // Force shutdown if tasks do not finish in time.
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}

