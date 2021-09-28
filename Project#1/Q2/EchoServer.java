import java.net.*;
import java.io.*;

public class EchoServer
{
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6017);
            /* now listen for connections */

                Socket client = sock.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

            while (true) {
                //Initiate an instant of InputStream class
                InputStreamReader input=new InputStreamReader(client.getInputStream());

                //Read the data from the client
                BufferedReader serverRead=new BufferedReader(input);

                String fromClient=serverRead.readLine();

                //output data from client
                System.out.println("Client: "+fromClient);

                fromClient=fromClient.replaceAll("\\bserver\\b","holder")
                        .replaceAll("\\bclient\\b","server")
                        .replaceAll("\\bholder\\b","client");// change the server word to client word and vice versa


                /* write the data from the client to the socket */
                pout.println("Server: "+fromClient);
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
