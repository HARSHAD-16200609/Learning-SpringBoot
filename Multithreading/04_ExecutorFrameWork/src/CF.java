import java.util.concurrent.*;

public class CF {

    public static void main(String [] args) throws InterruptedException, ExecutionException {


ExecutorService executor = Executors.newFixedThreadPool(2);
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }
            return "This is thread " +Thread.currentThread().getName();
        },executor).orTimeout(6000, TimeUnit.MILLISECONDS).exceptionally(x -> "ERROR OCCURED : " + x);

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }
            return "This is thread " +Thread.currentThread().getName();
        },executor).orTimeout(6000, TimeUnit.MILLISECONDS).exceptionally(x -> "ERROR OCCURED : " + x);

        CompletableFuture.allOf(f1, f2)
                .thenRun(() -> {
                    System.out.println(f1.join());
                    System.out.println(f2.join());

                })
                .join();
        System.out.println("Main Thread");

    }

}
