import java.sql.SQLOutput;

public class operations {
//    long  total;
//    long  total1;
//
//    public void calculate(long num){
//      total = 0;
//      int threadCount = 16;
//      World[] workers = new World[threadCount];
//
//      for(int i = 0 ; i < threadCount ; i++){
//          long lowerRange = (num / threadCount) * i;
//          // Last worker takes the remainder so no values are skipped.
//          long upperRange = (i == threadCount - 1) ? num : (num / threadCount) * (i + 1);
//          workers[i] = new World(lowerRange, upperRange,"worker "+(i+1));
////          workers[i].setPriority(Thread.MAX_PRIORITY); //10
////          workers[i].setPriority(Thread.MIN_PRIORITY); // 1
////          workers[i].setPriority(Thread.NORM_PRIORITY); // 5
//          System.out.println(workers[i].getName() + " will sum from " + lowerRange + " to " + (upperRange - 1));
//          workers[i].start();
//      }
//
//      for (World worker : workers) {
//          try {
//              worker.join();
//              this.total += worker.total;
//          } catch (InterruptedException e) {
//              Thread.currentThread().interrupt();
//              throw new IllegalStateException("Thread interrupted while waiting for workers", e);
//          }
//      }
//    }
//
//    public void calculate2(long num){
//        total1 = 0;
//        for(int i = 0 ; i < num ; i++){
//          total1+=i;
//        }
//    }

}
