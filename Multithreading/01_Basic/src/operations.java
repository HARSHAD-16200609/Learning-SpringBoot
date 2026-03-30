public class operations {
    long  total;
    long  total1;

    public void calculate(long num){
      total = 0;
      int threadCount = 16;
      World[] workers = new World[threadCount];

      for(int i = 0 ; i < threadCount ; i++){
          long lowerRange = (num / threadCount) * i;
          // Last worker takes the remainder so no values are skipped.
          long upperRange = (i == threadCount - 1) ? num : (num / threadCount) * (i + 1);
          workers[i] = new World(lowerRange, upperRange);
          workers[i].start();
      }

      for (World worker : workers) {
          try {
              // Join every worker before reading its partial sum.
              worker.join();
              this.total += worker.total;
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              throw new IllegalStateException("Thread interrupted while waiting for workers", e);
          }
      }
    }

    public void calculate2(long num){
        total1 = 0;
        for(int i = 0 ; i < num ; i++){
          total1+=i;
        }
    }

}
