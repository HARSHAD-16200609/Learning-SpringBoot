

public class World extends Thread {

   World(String name){
   super(name);

   }

    @Override
    public void run(){
       try{

      Thread.sleep(1000);
           System.out.println("Thread is running");
       }catch(InterruptedException e){
           System.out.println("Thread Interupted "+ e);
       }
    }


}
