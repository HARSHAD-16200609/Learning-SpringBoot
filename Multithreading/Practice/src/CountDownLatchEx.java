import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchEx {
    public static void main(String args[]) throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(5);

        ExecutorService executor = Executors.newFixedThreadPool(5);
Runnable gun = ()->{

        try {
            System.out.println("Ready... Set...");
            Thread.sleep(1000);
            System.out.println("1");
            Thread.sleep(1000);
            System.out.println("2");
            Thread.sleep(1000);
            System.out.println("3");
            Thread.sleep(1000);
            System.out.println(">> BANG! <<");


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally{
            startLatch.countDown();  // 🔥 FIRE THE GUN
        }

};
        executor.submit(gun);

        for (int i = 0; i < 5; i++) {
            executor.submit(new Runner(startLatch, finishLatch));
        }

        finishLatch.await();

        System.out.println("Race ended");
        executor.shutdown();
    }
}


class Runner implements Runnable {
    int time;
    CountDownLatch startLatch;
    CountDownLatch finishLatch;

    Runner(CountDownLatch startLatch, CountDownLatch finishLatch) {
        this.startLatch = startLatch;
        this.finishLatch = finishLatch;
        this.time = 5000;
    }

    @Override
    public void run() {
        try {
            startLatch.await();  // 🔥 WAIT FOR GUN

            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.sleep(time);

            System.out.println(Thread.currentThread().getName() + " reached finish Line");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            finishLatch.countDown();  // 🔥 SIGNAL FINISH
        }
    }
}


