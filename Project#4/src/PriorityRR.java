// CECS-326-project-4
// Nhan Vo
// Kelly Duangrudeeswat
import java.util.List;

public class PriorityRR implements Algorithm{
    private List<Task> queue;
    private int qTime=10;
    // Your code here
    public PriorityRR(List<Task> queue){
        this.queue=queue;
    }

    //override schedule() method from Algorithm interface
    @Override
    public void schedule() {
        while(!queue.isEmpty()){// run until the queue is empty
            Task temp=pickNextTask();// call pickNextTask() to get the next available task in the queue
            System.out.println(temp.toString());// output the task
            if(temp.getBurst()<=qTime){//if the burst time is less than or equal to the quantum time, task can be completed
                System.out.println("Task "+ temp.getName()+" is finished\n");// signalling task is completed
            }
            if(temp.getBurst()>qTime){// else
                int remainTime=temp.getBurst()-qTime;// calculate the new burst time (remaining time)
                if(remainTime!=0){// double check if the remaining time is not 0
                    Task newtemp = temp;
                    temp.setBurst(remainTime);// set the new burst time for the task
                    queue.add(newtemp);// add it back to the end of the queue
                }
            }
            queue.remove(temp);// remove the current task

        }
    }

    //Override pickNextTask() method to choose the next available task in the queue
    @Override
    public Task pickNextTask() {
        Task temp=queue.get(0);// the default task is the first element in the queue
        if(queue.size()==1){// if queue only has one element
            return temp;// return temp
        }
        else{// if queue has more than one element
            int Maxpriority=queue.get(0).getPriority();// default max priority is the priority of the first element
            for(int i=0;i<queue.size();i++){// check if queue has a task with greater priority
                if(queue.get(i).getPriority()>Maxpriority){
                    Maxpriority=queue.get(i).getPriority();
                    temp=queue.get(i);
                }
            }
            return temp;// return the highest priority task
        }
    }
}
