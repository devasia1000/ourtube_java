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
   
   ArrayList<Integer> bytesReadFromClient=new ArrayList<Integer>();

   @Override
   public void run() {
      try {
          /* open input stream and read all the bytes */
          InputStreamReader clientReader=new InputStreamReader(sock.getInputStream());
          Integer b;
          while(clientReader.ready()){
              b=clientReader.read();
              bytesReadFromClient.add(b);
          }
          
          
          
          Socket s=new Socket();
          OutputStream serverWriter=s.getOutputStream();
          for(Integer integ : bytesReadFromClient){
              serverWriter.write(b);
          }
      }
      catch (Exception e) {
         System.out.println(e);
      }
   }
}