import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//DinignServerImpl class, implements DiningServer interface
// to act as a monitor and preventing deadlock
public class DiningServerImpl  implements DiningServer
{
    private int n=5;// default is 5 chopsticks and 5 philosophers
    enum states{THINKING, HUNGRY, EATING}// Create an enum for 3 states

    private states st[]=new states[n];// create an empty state array for the states of each philosopher
    private Condition cd[]=new Condition[n];// Condition array for each philosopher
    private ReentrantLock lock=new ReentrantLock();// Create a ReentrantLock


    //Take fork method. Will check if there are 2 chopsticks available to get
    //If a philosopher gets 2 chopsticks, he/she will be able to eat.
    //Else, they need to wait
    @Override
    public void takeForks(int philosopherNumber) throws InterruptedException {
        lock.lock();//lock the ReentrantLock to start the comparing process
        st[philosopherNumber]=states.HUNGRY;// set state of the philosopher to hungry
        System.out.println("Philosopher "+philosopherNumber+" is "+this.getPhilosopherState(philosopherNumber));// output the state
        System.out.println("Philosopher "+philosopherNumber+" is trying to take chopsticks");// notify that the philosopher is trying to take chopsticks
        test(philosopherNumber);// test the condition of the philosopher
        //lock.unlock();
        if(st[philosopherNumber]!=states.EATING){// If there are no pair of chopsticks available and 2 philosophers are eating but not this philosopher
                                                // he/she need to wait
            //cd[philosopherNumber].wait();
            cd[philosopherNumber].await();// signal condition to wait
        }
        lock.unlock();// unlock the ReentrantLock to finish the comparing process

    }

    //philosopher will return the chopsticks when finish eating
    @Override
    public void returnForks(int philosopherNumber) throws InterruptedException {
        lock.lock();//lock the ReentrantLock to start the comparing process
        st[philosopherNumber]=states.THINKING;// set the state of the philosopher to thinking
        System.out.println("Philoshoper "+ philosopherNumber + " release chopsticks");// notify that he/she is releasing the chopsticks

        //Check if the philosophers on the left and right of this philosopher need chopsticks
        test((philosopherNumber+4)%5);
        //System.out.println("Philosopher "+(philosopherNumber+4)%5+" is "+this.getPhilosopherState(philosopherNumber));
        test((philosopherNumber+1)%5);

        //lock.unlock();
        //System.out.println("Philosopher "+(philosopherNumber+1)%5+" is "+this.getPhilosopherState(philosopherNumber));
        System.out.println("Philosopher "+philosopherNumber+" is "+this.getPhilosopherState(philosopherNumber));// notify that this philosopher is back to thinking state
        //cd[philosopherNumber].wait();
        lock.unlock();// unlock the ReentrantLock to finish the comparing process


    }

    //Public constructor:
    //Take no parameters
    public DiningServerImpl() throws InterruptedException {
        int i=0;
        while(i<n){// initialize the numbers of philosophers, states, and conditions according to the number of n
            st[i]=states.THINKING;// set all philosophers state to thinking
            cd[i]=lock.newCondition();// initialize the condition
            //System.out.println(i);
            //System.out.println("Philosopher "+i+" is "+this.getPhilosopherState(i));
            i++;//increment pointer value
        }
    }

    public void setN(int num){// A method to change the number of chopsticks and philosophers if necessary
        n=num;
    }

    //Test function
    // To check each philosopher condition and the philosopher in both hand-sides
    private void test(int philosopherNumber) throws InterruptedException {
        //If the philosopher on the left hand side and the right hand side does not eating and this philosopher is hungry
        // There are chopsticks available to take
        if((st[(philosopherNumber+4)%5]!=states.EATING) && (st[philosopherNumber]==states.HUNGRY) && (st[(philosopherNumber+1)%5]!=states.EATING)){
            //System.out.println("Philosopher "+(philosopherNumber+1)%5+" is "+this.getPhilosopherState(philosopherNumber));
            System.out.println("Philosopher "+philosopherNumber+" got 2 chopsticks");// notify that this philosopher got a pair of chopsticks and ready to eat
            st[philosopherNumber]= states.EATING;//change state to eating
            cd[philosopherNumber].signal();//signal to other philosophers
            //PhilosopherCondition(philosopherNumber);
        }
        System.out.println("Philosopher "+philosopherNumber+" is "+this.getPhilosopherState(philosopherNumber));//notify that this philosopher is eating

    }

    //getPhilosopherState function
    //To get the state of a specific philosopher
    public states getPhilosopherState(int philosopherNumber){
        if(st[philosopherNumber]==states.THINKING){
            return states.THINKING;
        }
        else if(st[philosopherNumber]==states.EATING){
            return states.EATING;
        }
        else{
            return states.HUNGRY;
        }
    }

}
