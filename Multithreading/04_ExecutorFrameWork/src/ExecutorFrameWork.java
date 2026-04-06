import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorFrameWork {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executor.submit(() -> {
                long result = factorial(finalI);
                System.out.println(result);

            });
            // used to take something that returned inside the submit method
//            Future<?> future =  executor.submit(() -> {
//                long result = factorial(finalI);
//                System.out.println(result);
//            });
//             try {
//                 future.get();
//             } catch (Exception e) {
//                 Thread.currentThread().interrupt();
//             }

        }
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Total time " + (System.currentTimeMillis() - startTime));
    }

    private static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    // example of shutdown , shutdownNow (forced Shutdown) and awaitTermination (wait for the termination of the executor)
//    ExecutorService executor2 = Executors.newFixedThreadPool(3);

//try {
//        for (int i = 0; i < 5; i++) {
//            executor2.submit(() -> {
//                try {
//                    Thread.sleep(2000);
//                    System.out.println(Thread.currentThread().getName() + " done");
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt(); // preserve interrupt
//                }
//            });
//        }
//
//    } finally {
//        executor2.shutdown(); // 🔹 stop accepting new tasks
//
//        try {
//            if (!executor2.awaitTermination(5, TimeUnit.SECONDS)) {
//                executor2.shutdownNow(); // 🔥 force shutdown
//            }
//        } catch (InterruptedException e) {
//            executor2.shutdownNow();
//            Thread.currentThread().interrupt();
//        }
//    }

}



