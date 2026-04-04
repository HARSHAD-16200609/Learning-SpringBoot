import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    static int balance = 1000;

    private Bank(){

    }
    private static Lock lock = new ReentrantLock(true); // makes the variable acessing by threads fair similar to synchronize no  starvation

    public static void withdraw(int ammount) {

        if (ammount <= balance) {
            try {
                System.out.println(Thread.currentThread().getName() + " Trying to withdraw " + ammount);
                Thread.sleep(10000);
                // with synchronized  this will not allow other thread to access the method until the first thread finishes its execution
                // if t1 takes too much time t32 gets starved
                // therefore we used custom locks

            } catch (InterruptedException e) {
                System.out.println("Thread was interupted");
                Thread.currentThread().interrupt();
            }
            balance -= ammount;
            System.out.println(Thread.currentThread().getName() + " Withdrawn " + ammount);
            System.out.println("Remaining balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " Not enough balance to withdraw " + ammount);
        }


    }

    public static void withdrawWithLock(int amount) throws InterruptedException {
        if (lock.tryLock(500, TimeUnit.MILLISECONDS)) {
            try {
                if (balance >= amount) {
                    System.out.println(Thread.currentThread().getName() + " Trying to withdraw " + amount);

                    try {
                        Thread.sleep(1000); // simulate delay
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // this is done to set the interupt true for the thread the thread still runs fine.
//                        but we don't know the event ocuured to avoid this and use  the thread.isinterrupted() method to check if the thread was interupted or not and handle it accordingly
//                        we need to add the curretnthread.interupt to the thread in exception handling
                    }

                    balance -= amount;

                    System.out.println(Thread.currentThread().getName() + " Withdrawn " + amount);
                    System.out.println("Remaining balance: " + balance);

                } else {
                    System.out.println(Thread.currentThread().getName() + " Not enough balance");
                }
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + " Could not acquire lock");
        }
    }
}


// output of no wait for thread to unlock
//t1 Trying to withdraw 100
//t2 Could not acquire lock
//t1 Withdrawn 100
//Remaining balance: 900


// output of  wait for thread to unlock  <= delay
//t1 Trying to withdraw 100
//t2 Could not acquire lock
//t1 Withdrawn 100
//Remaining balance: 900


// output of no wait for thread to unlock  > delay
//t1 Trying to withdraw 100
//t1 Withdrawn 100
//Remaining balance: 900
//t2 Trying to withdraw 100
//t2 Withdrawn 100
//Remaining balance: 800
