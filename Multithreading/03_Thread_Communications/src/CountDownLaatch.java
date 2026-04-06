import java.util.concurrent.*;

public class CountDownLaatch {
    public static void main(String []args) throws InterruptedException {
        int n = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(n);

        // both way second way demonstrates the callback mechanism, where we can execute some code
        // when all the services
        // are up and running,
        // in this case we are printing a message that application
        // n is ready to accept requests on port 5000
//        CyclicBarrier barrier = new CyclicBarrier(n);

        CyclicBarrier barrier = new CyclicBarrier(n, new Runnable() {
            @Override
            public void run() {
                System.out.println("All services are up and running, application is ready to accept requests... on port 5000");
            }
        });
        executorService.submit(new DatabaseService(barrier));
        executorService.submit(new RedisService(barrier));
        executorService.submit(new KafkaService(barrier));
        // Main thread doesn't wait for these cylic barriers...
        System.out.println("Waiting for all services to be up and running... (Main)");
        barrier.reset();
        executorService.shutdown();
    }
}


 class DatabaseService implements Callable<String> {

    private CyclicBarrier barrier;

     DatabaseService(CyclicBarrier barrier){
        this.barrier = barrier;
    }


     @Override
     public String call() throws  Exception  {

             System.out.println("Connecting to MongoDB Server ap-south-1");
             Thread.sleep(8000);
         System.out.println("DB Connected...");
         System.out.println("Service waiting for the barrier");
         barrier.await();


         return null;
     }
 }
 class RedisService implements Callable<String> {

    private CyclicBarrier barrier;

     RedisService(CyclicBarrier barrier){
        this.barrier = barrier;
    }


     @Override
     public String call() throws  Exception  {

             System.out.println("Connecting to redis Server on port 26379 and initializing the cache...");
             Thread.sleep(4000);
         System.out.println("Redis DB Server Connected...");

         System.out.println("Service waiting for the barrier");
         barrier.await();
         return null;
     }
 }
 class KafkaService implements Callable<String> {

    private final CyclicBarrier barrier;

     KafkaService(CyclicBarrier barrier){
        this.barrier = barrier;
    }


     @Override
     public String call() throws  Exception  {

             System.out.println("Trying to connect to Kafka servers and initializing the queues..");
             Thread.sleep(6000);
         System.out.println("Kafka Servers Connected...");

         System.out.println("Service waiting for the barrier");
             barrier.await();
         return null;
     }
 }

 // output
