/**
 * An echo server listening on port 6007. This server reads from the client
 * and echoes back the result. 
 */

import java.net.*;
import java.io.*;

public class EchoServer
{
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6017);
            /* now listen for connections */
            while (true) {
                Socket client = sock.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

                //Initiate an instant of InputStream class
                InputStreamReader input=new InputStreamReader(client.getInputStream());

                //Read the data from the client
                BufferedReader serverRead=new BufferedReader(input);

                String fromClient=serverRead.readLine();

                //output data from client
                System.out.println("Client: "+fromClient);

                //check for breaking condition
                if(fromClient.equals("-1")){

                    /* close the socket and resume */
                    /* listening for connections */
                    client.close();
                    break;
                }
                /* write the data from the client to the socket */
                pout.println("Server: "+fromClient);
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
