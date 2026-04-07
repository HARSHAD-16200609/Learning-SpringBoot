import java.util.concurrent.*;

public class ThreadCreationComparison {
    public static void main (String [] args) throws ExecutionException, InterruptedException {
        RunnableExample runnableTask = new RunnableExample(5);
        Thread t1 = new Thread(runnableTask);
        t1.start();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CallableExample callableTask = new CallableExample(5);
        Future<Integer> result = executor.submit(callableTask);
        ExecutorService executor1 = Executors.newFixedThreadPool(2);
      Future<Integer> future =  executor1.submit(callableTask);


        System.out.println(result.get());
        System.out.println(future.get());
        executor.shutdown();
t1.join();
        System.out.println("This the Main Thread");

    }
}


class RunnableExample implements Runnable{
int num;

RunnableExample(int num){
    this.num = num;
}
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"Runable Square is "+ num*num);
    }
}
class CallableExample implements Callable {
int num;

    CallableExample(int num){
    this.num = num;
}

    @Override
    public Integer call() throws Exception {
        Thread.sleep(500);
       return num * num;
    }
}