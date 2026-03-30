public class myThread extends Thread {

  public  Counter counter;

   myThread(Counter counter){
   this.counter = counter;

   }

    @Override
    public void run(){

        for(int i = 0; i < 1000; i++){
            counter.count();
        }
    }

}
