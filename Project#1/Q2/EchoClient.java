import java.net.*;
import java.io.*;
import java.util.Scanner;

public class EchoClient
{
    public static void main(String[] args) {
        try {
            /* make connection to server socket */
            Socket sock = new Socket("127.0.0.1",6017);
            InputStream in = sock.getInputStream();
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            //Writer send input to server
            PrintWriter clientOut = new PrintWriter(sock.getOutputStream(), true);

            //Make sure client connect to server
            clientOut.println("Connected to server");
            String fromServer=bin.readLine();
            System.out.println(fromServer);

            //scanner to take user input
            Scanner scan=new Scanner(System.in);
            String userInput="";

            while(true){// send user input to the server
                System.out.println("*------------------------------------------------*");

                System.out.println("Hello Samurai.\nYou are sending a message to CyberBug 2077 server.\nEnter anything for message and -1 to close");//welcome message

                System.out.println("*------------------------------------------------*\n");

                userInput=scan.nextLine();

                if(userInput.equals("-1")){// close condition
                    sock.close();
                }

                clientOut.println(userInput);// send message to server

                /* read the quote from the socket */
                fromServer=bin.readLine();

                if(fromServer!=null){// Check to see if user enter anything
                    System.out.println(fromServer);
                }

            }

        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}