import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DiningServerImpl  implements DiningServer
{
    private int n=5;// default is 5 chopsticks and 5 philosophers
    enum states{THINKING, HUNGRY, EATING}

    private states st[]=new states[n];
    private Condition cd[]=new Condition[n];
    private ReentrantLock lock=new ReentrantLock();

    //Take fork method
    @Override
    public void takeForks(int philosopherNumber) throws InterruptedException {
        lock.lock();
        st[philosopherNumber]=states.HUNGRY;
        System.out.println("Philosopher "+philosopherNumber+" is taking chopsticks");
        test(philosopherNumber);
        if(st[philosopherNumber]!=states.EATING){
            cd[philosopherNumber].wait();
            //cd[philosopherNumber].await();
        }
        lock.unlock();

    }

    //return fork when finish eating
    @Override
    public void returnForks(int philosopherNumber) throws InterruptedException {
        lock.lock();
        st[philosopherNumber]=states.THINKING;
        test((philosopherNumber+4)%5);
        test((philosopherNumber+1)%5);
        System.out.println("Philoshoper "+ philosopherNumber + " release chopsticks");
        System.out.println("Philosopher "+philosopherNumber+" is "+this.getPhilosopherState(philosopherNumber));
        lock.unlock();
    }

    //Public constructor:
    public DiningServerImpl() throws InterruptedException {
        int i=0;
        while(i<n){
            st[i]=states.THINKING;
            cd[i]=lock.newCondition();
            //System.out.println(i);
            i++;
        }
    }

    public void setN(int num){
        n=num;
    }

    private void test(int philosopherNumber) throws InterruptedException {
        if((st[(philosopherNumber+4)%5]!=states.EATING) &&
                (st[philosopherNumber]==states.HUNGRY) &&
                (st[(philosopherNumber+1)%5]!=states.EATING)){
            st[philosopherNumber]= states.EATING;
            cd[philosopherNumber].signal();
            System.out.println("Philosopher "+philosopherNumber+" is "+this.getPhilosopherState(philosopherNumber));
        }
    }

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
