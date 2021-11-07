import java.util.ArrayList;

// Singleton Road class, acts as the only road connect west_village and east_village
public class Road {
    private static Road roadInstance;// static instance of the Road class
    private static int sequence=0;// binary mutex
    private static ArrayList<village> vi=new ArrayList<>();// list of villagers who are going on the road or waiting to go (first come, first serve)
    private static int index=0;// index of each villager


    // acquire() method of mutex
    public void acquire() throws InterruptedException {
        synchronized (vi){// synchronized tool
            while(sequence!=0){// if mutex is not 0, means someone is occupied the road,
                vi.wait();// then signal wait() to all other villagers
            }
            sequence++;// if road is available, update the sequence to make sure no one else can be on the road.
        }
    }

    //release() method of mutex
    public void release() {
        synchronized (vi){// synchronized tool
            sequence--;// update sequence to let other villager on the road
            vi.notify();// signal to other villagers that the road is available
        }
    }

    // private constructor of singleton class
    private Road(){}

    //public static getRoadInstance() method
    public static Road getRoadInstance(village v){
        if (roadInstance==null){// if no roadInstance was created
            roadInstance= new Road();// create a new roadInstance
            vi.add(v);// add the villager to the road
        }
        else{// if a road already created
            vi.add(v);// just add the villager without creating a new road
        }
        return roadInstance;// return the roadInstance
    }

}
