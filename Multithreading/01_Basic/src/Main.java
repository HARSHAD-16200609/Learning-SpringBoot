
public class Main {

    public static void main(String [] args) throws InterruptedException{
        World t2 =new World("Interupted Thread");
        t2.start();
        t2.interrupt();
    }


}
