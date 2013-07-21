
/**
 *
 * @author Devasia
 */

import java.net.*;

public class Main {
    
    static int port=80;

    public static void main(String args[]) throws Exception {
        ServerSocket ssock = new ServerSocket(port);
        while (true) {
            Socket sock = ssock.accept();
            new Thread(new MultiThreadedServer(sock)).start();
        }
    }
}
