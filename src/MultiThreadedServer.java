/**
 *
 * @author Devasia
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MultiThreadedServer implements Runnable {
   Socket sock;
    MultiThreadedServer(Socket csocket) {
        this.sock = csocket;
    }

    @Override
    public void run() {
        try {
            /* open input stream and read all the bytes */
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            InputStream is=sock.getInputStream();
            
            byte[] buf = new byte[4096];
            while (true) {
                int n = is.read(buf);
                if (n < 0) {
                    break;
                }
                baos.write(buf, 0, n);
            }

            byte data[] = baos.toByteArray();

            /* parse the request */
            System.out.println(new String(data));
            HTTPParser parse = new HTTPParser(new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}