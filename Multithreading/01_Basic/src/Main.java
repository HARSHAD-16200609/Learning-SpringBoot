
public class Main {

    public static void main(String [] args) throws InterruptedException{
        World t1 =new World("THREAD1");
        World t2 =new World("THREAD2");

        t1.setDaemon(true);// jvm dosees not wait for daemon threads to finish before exiting the program
        t2.setDaemon(true);
        t1.start();
        t2.start();

        System.out.println("Main thread..");


    }


}
