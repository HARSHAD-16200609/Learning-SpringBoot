
public class Main {

    public static void main(String [] args) throws InterruptedException{

        Counter counter  = new Counter();
        // single object shared between the two threads so the loop run's 2 times but the threads sometimes increment the same value
        // ex - both thread increment the count 101 to 102 instead of 103 so 2000 is uncertain to occur
        // to solve this problem and implement synchronisation between the threads
        // we use the synchronized keyword in the count method of the Counter class so only one thread can access it at a time
        // and the other thread will wait until the first thread finishes its execution and releases the lock on the method

        myThread t1 = new myThread(counter);
        myThread t2 = new myThread(counter);
        t1.start();
        t2.start();

try{
    t1.join();
    t2.join();

}catch(InterruptedException e){

}
        System.out.println( t1.counter.getCount());

    }


}
