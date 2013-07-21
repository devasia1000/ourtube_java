/**
 *
 * @author Devasia
 */

import java.net.*;

public class MultiThreadedServer implements Runnable {
   Socket csocket;
   MultiThreadedServer(Socket csocket) {
      this.csocket = csocket;
   }

   public void run() {
      try {
         
      }
      catch (Exception e) {
         System.out.println(e);
      }
   }
}