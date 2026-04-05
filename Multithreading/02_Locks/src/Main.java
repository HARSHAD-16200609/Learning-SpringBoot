import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {

        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {

                    Bank.withdrawWithLock(100);
                } catch (InterruptedException e) {
                    System.out.println(e);

                }

            }
        };

        Thread t1 = new Thread(task,"t1");
        Thread t2 = new Thread(task,"t2");
t1.start();
t2.start();


    }
}