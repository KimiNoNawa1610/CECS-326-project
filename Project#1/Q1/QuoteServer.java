/**
 * Quote server listening to port 6017.
 *
 */
 
import java.net.*;
import java.io.*;
import java.util.Random;

public class QuoteServer
{
  public static void main(String[] args) {
        //Array contain quotes
        String QuoteofDate[]={"A simple 'hello' could lead to a million things.",
                              "The world isn't perfect. But it's there for us doing the best it can. And that's what makes it so beautiful.",
                              "There are things that cannot be taken back. But the world will keep on spinning whether you laugh or you cry.",
                              "Dans une grande ame tout est grand.",
                              "People don't see does not mean they can't. They just don't want to",
                              "A bad day does not make a bad person.",
                              "Keep walking, there will be someone waits for you at the end",
                              "Family is the core of happiness."};
        Random ran=new Random();
        try {
            ServerSocket sock = new ServerSocket(6017);
            /* now listen for connections */

            while (true) {

                Socket client = sock.accept();

                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                /* write the Date to the socket */

                //pout.println(new java.util.Date().toString());
                //Modify to print out quote of the date instead of current date


                int maxRan=ran.nextInt(QuoteofDate.length-1);// maximum random number

                pout.println(QuoteofDate[maxRan]);// choose a random quote from quote array and print it out

                /* close the socket and resume */

                /* listening for connections */
                client.close();
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
