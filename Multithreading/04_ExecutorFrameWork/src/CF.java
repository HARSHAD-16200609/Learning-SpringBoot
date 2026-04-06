import java.util.concurrent.*;

public class CF {

    public static void main(String [] args) throws InterruptedException, ExecutionException {



        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }
            return "This is thread " +Thread.currentThread().getName();
        }).orTimeout(1000, TimeUnit.SECONDS).exceptionally(x -> "ERROR OCCURED : " + x);

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
            }
            return "This is thread " +Thread.currentThread().getName();
        }).orTimeout(1000, TimeUnit.SECONDS).exceptionally(x -> "ERROR OCCURED : " + x);

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println("Main Thread");

    }

}
