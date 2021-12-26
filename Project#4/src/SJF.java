// CECS-326-project-4
// Nhan Vo
// Kelly Duangrudeeswat
import java.util.List;

public class SJF implements Algorithm{
    private List<Task> queue;
    //public constructor
    public SJF(List<Task> queue){
        this.queue=queue;// set the queue
    }

    //override schedule() method from Algorithm interface
    @Override
    public void schedule() {
        int n=queue.size();// take the queue' size
        float avg=0;// initialize average runtim
        while(!queue.isEmpty()){// run until the queue is empty
            Task temp=pickNextTask();// call puckNextTask() to get the next available task in the queue
            System.out.println(temp.toString());// output task as a string
            System.out.println("Task "+temp.getName()+" is finished\n");// signalling task is finished
            avg+=temp.getBurst();// increment average waiting time
            queue.remove(temp);// remove finished task from queue
        }
        System.out.println("\nAverage waiting time: "+avg/n);// finally, output average waiting time

    }

    //Override pickNextTask() method to choose the next available task in the queue
    @Override
    public Task pickNextTask() {
        Task temp=queue.get(0);// default task will be the first element in the queue
        if(queue.size()==1){// if the queue only has one element
            return temp;// just return temp
        }
        else{// if queue has more than one element
            int minBurst=queue.get(0).getBurst();// default minBurst will be the burst of the first element
            for(int i=0;i<queue.size();i++){// check for the shortest burst time
                if(queue.get(i).getBurst()<minBurst){
                    minBurst=queue.get(i).getBurst();
                    temp=queue.get(i);
                }
            }
            return temp;// return the task with shortest burst time
        }
    }


}
