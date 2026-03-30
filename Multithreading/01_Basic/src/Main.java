
public class Main {

    public static void main(String [] args){
//
//        System.out.println("Main thread started.. ");
//        World t1 = new World();
//        System.out.println(t1.getState());  //NEW
//        t1.start();
//        System.out.println(t1.getState());  //RUNNABLE
//
//        t1.sleep(1000);
//        t1.join();
//        System.out.println(t1.getState());  //TERMINATED
//        System.out.println("Main thread Ended.. ");



        // Use the same input size in both methods for a fair time comparison.
        long input = 100_000_0000L;

        long start = System.currentTimeMillis();
        operations op = new operations();
        op.calculate(input);
        System.out.println(op.total);

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start) + " ms");


        long start1 = System.currentTimeMillis();

        operations op2 = new operations();
        op2.calculate2(input);
        System.out.println(op2.total1);

        long end1 = System.currentTimeMillis();

        System.out.println("Time taken: " + (end1 - start1) + " ms");



    }


}
