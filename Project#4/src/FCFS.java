/**
 * FCFS scheduling algorithm.
 */
// CECS-326-project-4
// Nhan Vo
// Kelly Duangrudeeswat
import java.util.*;
public class FCFS implements Algorithm{
    private List<Task> queue;
    //Your code here
    public FCFS(List<Task> queue){
        this.queue=queue;
    }

    //override schedule() method from Algorithm interface
    @Override
    public void schedule() {
        int burst=0;// initialize total burst time to calculate average waiting time
        for(int i=0;i<queue.size();i++){// iterate through all tasks in the queue
            System.out.println(queue.get(i).toString());// output the task
            System.out.println("Task "+queue.get(i).getName()+" is finished\n");// signalling task is finished
            burst+=queue.get(i).getBurst();// increment total burst time
        }
        System.out.println("\nAverage waiting time: "+((float)burst/queue.size()));// finally, output the average waiting time

    }

    //Override pickNextTask() method to choose the next available task in the queue
    @Override
    public Task pickNextTask() {
        return null;// since this is first come first server scheduling, we do not need pickNextTask()
    }
}
