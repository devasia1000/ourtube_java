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
          InputStreamReader clientReader=new InputStreamReader(sock.getInputStream());
          char[] bytesReadFromClient=null;
          clientReader.read(bytesReadFromClient);
          clientReader.close();
          
          HTTPParser parse=new HTTPParser(new String(bytesReadFromClient));
      }
      catch (Exception e) {
         System.out.println(e);
      }
   }
}