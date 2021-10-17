public interface DiningServer
{
    /* Called by a philosopher when it wishes to eat */
    public void takeForks(int philosopherNumber) throws InterruptedException;



    /* Called by a philosopher when it is finished eating */
    public void returnForks(int philosopherNumber) throws InterruptedException;

}