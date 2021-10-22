import java.util.Random;

//Philosopher class
// act as a philosopher object in the table
//could cause deadlock
public class Philosopher implements Runnable
{
    private int iD;
    private DiningServerImpl table;
    //private boolean isFinished=false;

    //public constructor
    //Take two parameters, num of iD and DiningServerImpl for table
    public Philosopher(int num, DiningServerImpl din){
        iD=num;
        table=din;
    }

    //run method to run the Runnable object
    @Override
    public void run() {
        System.out.println("Philoshopher "+ iD+ " is in the table");// notify that a philosopher is in the table (DiningServerImpl)
        Random ran=new Random();// create a random object to generate random number
        while(true){//run forever if no deadlock occurs
            try {// try if no error
                table.takeForks(iD);// call takeForks method to get the chopsticks and eat
                Thread.sleep(ran.nextInt(3000-1000+1)+1000);// let the philosopher sleep for a random time from 1000 milliseconds to 3000 milliseconds

                //System.out.println("Philoshoper "+iD+" is " + table.getPhilosopherState(iD));
                //Thread th=new Thread(this);
                //System.out.println("Philoshoper " + iD + " is " + table.getPhilosopherState(iD));
                //Thread.sleep(ran.nextInt(3000));

                table.returnForks(iD);// call returnForks
                //isFinished=true;
                System.out.println("Philoshoper " + iD + " is " + table.getPhilosopherState(iD));// notify the current state of philosopher after at the end of the run


            } catch (InterruptedException e) {// if error
                e.printStackTrace();// output the error
            }
        }

    }

}

