/**
 * Modified DateClient so that it requests a quote
 * from the quote server.
 */
 
import java.net.*;
import java.io.*;

public class QuoteClient
{
	public static void main(String[] args) {
        try {
            /* make connection to server socket */
            Socket sock = new Socket("127.0.0.1",6017);
            InputStream in = sock.getInputStream();

            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            /* read the quote(s) from the socket */

            String line;

            while ( (line = bin.readLine()) != null)

                System.out.println(line);

            /* close the socket connection*/

            sock.close();

        }

        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}
