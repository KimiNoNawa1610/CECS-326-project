public class Philosopher implements Runnable
{
    private int iD;
    private DiningServerImpl table;

    //public constructor
    public Philosopher(int num, DiningServerImpl din){
        iD=num;
        table=din;
    }

    @Override
    public void run() {
        System.out.println("Philoshopher "+ iD+ " is in the table");

        try {
            table.takeForks(iD);
            table.returnForks(iD);
            //System.out.println("Philoshoper is " + table.getPhilosopherState(iD));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

