import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLaatch {
    public static void main(String []args) throws InterruptedException {
        int n = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        CountDownLatch latch = new CountDownLatch(n);
        executorService.submit(new DatabaseService(latch));
        executorService.submit(new RedisService(latch));
        executorService.submit(new KafkaService(latch));
        latch.await();
        System.out.println("All services are up and running, application is ready to accept requests... on port 5000");
        executorService.shutdown();
    }
}


 class DatabaseService implements Callable<String> {

    private CountDownLatch latch;

     DatabaseService(CountDownLatch latch){
        this.latch = latch;
    }


     @Override
     public String call() throws  Exception  {
         try {
             System.out.println("Connecting to MongoDB Server ap-south-1");
             Thread.sleep(8000);
         }  finally {
             latch.countDown(); // Decrement the latch count
         }
         return null;
     }
 }
 class RedisService implements Callable<String> {

    private CountDownLatch latch;

     RedisService(CountDownLatch latch){
        this.latch = latch;
    }


     @Override
     public String call() throws  Exception  {
         try {
             System.out.println("Connecting to redis Server on port 26379 and initializing the cache...");
             Thread.sleep(4000);
         }  finally {
             latch.countDown(); // Decrement the latch count
         }
         return null;
     }
 }
 class KafkaService implements Callable<String> {

    private CountDownLatch latch;

     KafkaService(CountDownLatch latch){
        this.latch = latch;
    }


     @Override
     public String call() throws  Exception  {
         try {
             System.out.println("Trying to connect to Kafka servers and initializing the queues..");
             Thread.sleep(6000);
         }  finally {
             latch.countDown(); // Decrement the latch count
         }
         return null;
     }
 }