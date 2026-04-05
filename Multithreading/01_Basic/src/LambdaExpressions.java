public class LambdaExpressions {
    public static void main(String[] args) {
        // This lambda is the Runnable task: each thread will execute this same block independently.
        Runnable task = () -> {
            // Print 5 iterations so you can see both threads interleave their output.
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " is running iteration " + i);
            }
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");

        thread1.start();
        thread2.start();

    }
}

// the lambda expressions can be used on interfaces that have only one abstract method, such as Runnable,
// Callable, Comparator, etc.
// This allows us to write more concise and readable code without the need for anonymous inner classes.
// we can remove the round bracket from parameters if only single parameter is there
// and also we can remove the return type if it is a single statement.
