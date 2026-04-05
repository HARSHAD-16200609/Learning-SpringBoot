public class Counter {
    int count;

    Counter(){

    }
    public  void  count(){
        synchronized(this){
            // this is used to synchronize an particular block of code
        count++;
        }
    }
    public int getCount() {
        return count;
    }

}
