

public class World extends Thread {

   public long start;
   public long end;
   public long total;
   World(long start, long end){
       this.start = start;
       this.end = end;

   }

    @Override
    public void run(){
        for (long i = start ; i < end ;i++){
             total+=i;
        }
    }


}
