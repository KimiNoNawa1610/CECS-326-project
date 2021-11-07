import java.util.Random;

//abstract village class extends Thread, acts as an ancestor of every other villages
abstract class village extends  Thread{
    protected enum direction{east_village, west_village}// declare enum value for different type of village
    protected direction name;// every village will have a name depend on the direction
    protected int id;// every village will have a unique id
    protected Random ran=new Random();// random number generator
    protected Road road;// all villages will have only on road
    public abstract void from();// abstract method to declare the name and direction of a village.

    //village super constructor
    public village(int num){
        this.id=num;// set the id
        this.road=Road.getRoadInstance(this);// assign the village to the road
    }

    //run method of all villages
    @Override
    public void run(){
        System.out.println(this.toString()+" is trying to get on the road");// first, signal that the villager is trying to enter the road
        int sleeptime=ran.nextInt(3000-1000+1)+1000;// calculate the time that the villager will move on the road
        try {
            road.acquire();// call road mutex acquire() method to check if the villager can be on the road
            System.out.println(this.toString()+" is on the road for a selfie for "+(float)sleeptime/1000 +" seconds");// if the villager can be on the road. Signal output
            this.sleep(sleeptime);// let the villager (thread) sleep for couple seconds
            System.out.println(this.toString()+" is leaving the road");// after the sleeping time, the villager will signal a leaving statement
            road.release();// then call the road's release() method to signal the end of the villager's journey.
        } catch (InterruptedException e) {
            e.printStackTrace();// if error, output the error message
        }
    }

    // override toString() method to output the villager statements
    public String toString(){
        if (name==direction.east_village){
            return id+" from east_village";// return a string with villager id and the east_village direction
        }
        return id+" from west_village";// else, return a string with villager id and the west_village direction

    }

}
