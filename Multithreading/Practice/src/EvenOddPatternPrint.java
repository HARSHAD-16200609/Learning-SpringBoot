
public class EvenOddPatternPrint {
    public static void main(String[] args) {

         Thread t1 = new Thread(()->{
             for(int i = 0 ; i <= 20 ; i++){
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
                 if((i & 1) == 0){
                     System.out.println("Even " +i);
                 }
             }
         });

         Thread t2 = new Thread(()->{
             for(int i = 0 ; i < 20 ; i++){
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     throw new RuntimeException(e);
                 }
                 if((i & 1) != 0){
                     System.out.println("Odd "+i);
                 }
             }
         });
        t1.start();
         t2.start();

        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
           Thread.currentThread().interrupt();
        }
        System.out.println("Main thread");
    }
}