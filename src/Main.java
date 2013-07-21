
/**
 *
 * @author Devasia
 */

import java.net.*;

public class Main {

    public static void main(String args[])
            throws Exception {
        ServerSocket ssock = new ServerSocket(1234);
        System.out.println("Listening");
        while (true) {
            Socket sock = ssock.accept();
            System.out.println("Connected");
            new Thread(new MultiThreadedServer(sock)).start();
        }
    }
}
