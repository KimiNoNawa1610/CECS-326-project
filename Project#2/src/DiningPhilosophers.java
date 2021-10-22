import java.util.ArrayList;

//DiningPhilosophers
//driver class to run and test the DiningServerImpl
public class DiningPhilosophers {
    public static void main(String args[]) throws InterruptedException {
        // Here your code

        DiningServerImpl table=new DiningServerImpl();// Initialize a monitor (DiningServerImpl)
        //ArrayList<Thread> threadArrayList=new ArrayList<>();
        //ArrayList<Philosopher> philosopherArrayList=new ArrayList<>();
        int i=0;// initialize a pointer value
        while(i<5){// while loop to create philosopher object and thread
            Philosopher ph=new Philosopher(i,table);// create philosopher object
            //philosopherArrayList.add(ph);
            Thread th=new Thread(ph);// create a new thread for each philosopher
            //threadArrayList.add(th);
            th.start();// start the thread
            i++;// update the pointer value
        }
        /*
        while(true){
            for(int j=0;j<threadArrayList.size();j++){
                threadArrayList.get(j).start();
            }
        }*/
    }
}
