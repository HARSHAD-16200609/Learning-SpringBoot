public class LambdaExpressions {
    public static void main(String[] args) {
        // a way to create a thread using lambda expression
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is running iteration " + i);
            }
        }, "Thread-1");
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is running iteration " + i);
        }

    }, "Thread-2");

        thread1.start();
        thread2.start();

    }
}

// the lambda expressions can be used on interfaces that have only one abstract method, such as Runnable,
// Callable, Comparator, etc.
// This allows us to write more concise and readable code without the need for anonymous inner classes.
// we can remove the round bracket from parameters if only single parameter is there
// and also we can remove the return type if it is a single statement.
