import java.util.Random;

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
        //Random ran=new Random();

        try {
            table.takeForks(iD);
            //System.out.println("Philoshoper "+iD+" is " + table.getPhilosopherState(iD));
            //Thread th=new Thread(this);
            //th.sleep(ran.nextInt(3000));
            table.returnForks(iD);
            System.out.println("Philoshoper "+iD+" is " + table.getPhilosopherState(iD));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

