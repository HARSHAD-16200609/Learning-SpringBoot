public class RaceCondition {
    public static void main(String [] args){
     Counter counter = new Counter();
 CounterThread ct1 = new CounterThread(counter);
 CounterThread ct2 = new CounterThread(counter);
        ct1.start();
        ct2.start();
    try {  ct1.join();
        ct2.join();
    } catch (InterruptedException e) {
    }
}
}

class Counter {
    int count;
    Counter(){
        count=0;
    }
}

class CounterThread extends Thread {
    private  Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (counter){
                counter.count++;
                System.out.println(counter.count + " This count is incremented by " + Thread.currentThread().getName());
            }


        }
    }
}

// thread safe output will be like this
//        1 This count is incremented by Thread-0
//        2 This count is incremented by Thread-0
//        3 This count is incremented by Thread-0
//        4 This count is incremented by Thread-0
//        5 This count is incremented by Thread-0
//        6 This count is incremented by Thread-0
//        7 This count is incremented by Thread-0
//        8 This count is incremented by Thread-0
//        9 This count is incremented by Thread-0
//        10 This count is incremented by Thread-0
//        11 This count is incremented by Thread-1
//        12 This count is incremented by Thread-1
//        13 This count is incremented by Thread-1
//        14 This count is incremented by Thread-1
//        15 This count is incremented by Thread-1
//        16 This count is incremented by Thread-1
//        17 This count is incremented by Thread-1
//        18 This count is incremented by Thread-1
//        19 This count is incremented by Thread-1
//        20 This count is incremented by Thread-1

// but without synchronization, the output may be like this

//        2 This count is incremented by Thread-1
//        3 This count is incremented by Thread-1
//        4 This count is incremented by Thread-1
//        5 This count is incremented by Thread-1
//        6 This count is incremented by Thread-1
//        7 This count is incremented by Thread-1
//        8 This count is incremented by Thread-1
//        1 This count is incremented by Thread-0
//        10 This count is incremented by Thread-0
//        11 This count is incremented by Thread-0
//        9 This count is incremented by Thread-1
//        12 This count is incremented by Thread-0
//        13 This count is incremented by Thread-1
//        14 This count is incremented by Thread-0
//        15 This count is incremented by Thread-1
//        16 This count is incremented by Thread-0
//        17 This count is incremented by Thread-0
//        18 This count is incremented by Thread-0
//        19 This count is incremented by Thread-0
//        20 This count is incremented by Thread-0
