/**
 * Non-preemptive priority scheduling algorithm.
 */
// CECS-326-project-4
// Nhan Vo
// Kelly Duangrudeeswat
import java.util.*;
public class Priority implements Algorithm{
// Your code here
    private List<Task> queue;
    public Priority(List<Task> queue){
        this.queue=queue;
    }

    //override schedule() method from Algorithm interface
    @Override
    public void schedule() {
        int n=queue.size();// get the queue's size
        float avg=0;// initialize the average waiting time
        while(!queue.isEmpty()){// run until the queue is empty
            Task temp=pickNextTask();// call pickNextTask() to pick the next available task in the queue
            System.out.println(temp.toString());// output the task
            System.out.println("Task "+temp.getName()+" is finished\n");// signalling task is finished
            avg+=temp.getBurst();// increment average waiting time
            queue.remove(temp);// remove current task
        }
        System.out.println("\nAverage waiting time: "+avg/n);// finally, output average waiting time
    }

    //Override pickNextTask() method to choose the next available task in the queue
    @Override
    public Task pickNextTask() {
        Task temp=queue.get(0);// default task is the first element in the queue
        if(queue.size()==1){// if the queue only has one element
            return temp;//return temp
        }
        else{// if queue has more than one element
            int Maxpriority=queue.get(0).getPriority();// the default max priority is the first element in the queue
            for(int i=0;i<queue.size();i++){// check if there is a higher priority
                if(queue.get(i).getPriority()>Maxpriority){
                    Maxpriority=queue.get(i).getPriority();
                    temp=queue.get(i);
                }
            }
            return temp;// return task with the highest priority
        }
    }
}