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
            BufferedReader rd=new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String line=null, mess="";
            while((line=rd.readLine())!=null){
                if(line.equals("")){
                    break;
                }
                
                else{
                    mess=mess+line+"\n";
                }
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}