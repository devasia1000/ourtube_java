
/**
 *
 * @author Devasia
 */

import java.net.*;

public class Main {
    
    static int port=80;

    public static void main(String args[]) throws Exception {
        ServerSocket ssock = new ServerSocket(port);
        System.out.println("Listening on port "+port);
        while (true) {
            Socket sock = ssock.accept();
            System.out.println("Connected");
            new Thread(new MultiThreadedServer(sock)).start();
        }
    }
}
