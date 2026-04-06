import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SingleThreadExecutorExample {

    public static void main(String[] args) {
        // Single worker thread: tasks run one-by-one in submission order.
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            List<Future<Long>> futures = new ArrayList<>();

            for (int i = 1; i < 10; i++) {
                final int n = i; // Capture loop value so each lambda uses the correct number.
                futures.add(executor.submit(() -> {
                    // Lambda task: compute factorial for this specific captured value.
                    return factorial(n);
                }));
            }

            // Reading futures in order keeps output deterministic for this example.
            for (Future<Long> future : futures) {
                System.out.println(future.get());
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Preserve interrupt status.
        } catch (ExecutionException e) {
            System.err.println("Task failed: " + e.getCause());
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 1; i <= n; i++) result *= i;
        return result;
    }
}
