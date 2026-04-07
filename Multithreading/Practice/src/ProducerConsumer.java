import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {
    public static void main(String[] args) throws InterruptedException {
        SharedResource sr = new SharedResource();

            Thread producer = new Thread(new Producer(sr));

        ExecutorService executor = Executors.newFixedThreadPool(10 );
        executor.submit(new Consumer(sr));


        for (int i = 1; i <= 5; i++) {
            executor.submit(new Consumer(sr));
        }

        producer.start();


        Thread.sleep(10000);

        System.out.println("This is the end of Main Thread");
    }
}

class SharedResource {
    boolean hasData = false;
    int data;

    SharedResource() {
        data = 0;
    }
}

class Producer implements Runnable {
    SharedResource sr;

    Producer(SharedResource sr) {
        this.sr = sr;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1000); // simulate production
            } catch (InterruptedException e) {
                break;
            }

            synchronized (sr) {
                while (sr.hasData) {
                    try {
                        sr.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();

                    }
                }

                sr.data++;
                System.out.println("Produced: " + sr.data);

                sr.hasData = true;
                sr.notifyAll();
            }
        }
    }
}

class Consumer implements Runnable {
    SharedResource sharedResource;

    Consumer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {  // ← keep consuming
            synchronized (sharedResource) {
                while (!sharedResource.hasData) {
                    try {
                        sharedResource.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("Consumed: " + sharedResource.data
                        + " by " + Thread.currentThread().getName());
                sharedResource.hasData = false;
                sharedResource.notifyAll();
            }

            try {
                Thread.sleep(500); // optional: simulate processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}