public class DiningPhilosophers {
    public static void main(String args[]) throws InterruptedException {
        // Here your code
        DiningServerImpl table=new DiningServerImpl();
        int i=0;
        while(i<5){
            Philosopher ph=new Philosopher(i,table);
            Thread th=new Thread(ph);
            th.start();
            i++;
        }

    }
}
