public class World extends Thread {

   World(String name){
   super(name);

   }

    @Override
    public void run(){


        while(true) {
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield(); // hints jvm that the thread is willing to relenquish the control}

        }
    }

}
