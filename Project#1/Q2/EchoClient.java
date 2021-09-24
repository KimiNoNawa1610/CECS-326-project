/**
 * An echo client. The client enters data to the server, and the
 * server echoes the data back to the client.
 */
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

            //Output when the client is connected to server
            clientOut.println("Connected to Server");


            //scanner to take user input
            Scanner scan=new Scanner(System.in);
            String userInput="";

            while(true){// send user input to the server
                System.out.println("Hello Samurai.\nYou are sending a message to CyberBug 2077 server.\nEnter anything for message and -1 to close");//welcome message
                userInput=scan.nextLine();
                clientOut.println(userInput);// send message to server

                if(userInput.equals("-1")){
                    sock.close();
                }
                /* read the quote from the socket */
                String fromServer=bin.readLine();

                if(fromServer!=null){
                    System.out.println(fromServer);
                }

            }

        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }