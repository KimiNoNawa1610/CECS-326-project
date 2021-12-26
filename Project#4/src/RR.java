/**
 * Non-preemptive priority scheduling algorithm using RR.
 *
 * This algorithm will run tasks according to round-robin scheduling.
 */
// CECS-326-project-4
// Nhan Vo
// Kelly Duangrudeeswat
import java.util.*;
public class RR implements Algorithm{
    private List<Task> queue;
    private int qTime=10;
// Your code here
    public RR(List<Task> queue){
        this.queue=queue;
    }

    //override schedule() method from Algorithm interface
    @Override
    public void schedule() {
        while(!queue.isEmpty()){// run until the queue is empty
            Task temp=pickNextTask();// call pickNextTask() to get the next available task
            System.out.println(temp.toString());// output the task
            if(temp.getBurst()<=qTime){// if the bursttime is less than quantum time, then the task can be finished
                System.out.println("Task "+ temp.getName()+" is finished\n");
            }
            if(temp.getBurst()>qTime){// if not, we reschedule the task, update the burst time and add it at the end of the queue
                int remainTime=temp.getBurst()-qTime;// new burst time
                if(remainTime!=0){// double check to make sure the burst time is not 0
                    Task newtemp = temp;
                    temp.setBurst(remainTime);// set new burst time
                    queue.add(newtemp);// add the task to the end of the queue
                }
            }
            queue.remove(temp);// remove the current task.
        }
    }

    //Override pickNextTask() method to choose the next available task in the queue
    @Override
    public Task pickNextTask() {
        return queue.get(0);// just return the first element of the queue
    }
}