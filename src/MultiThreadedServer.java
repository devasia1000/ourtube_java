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

            /* parse HTTPRequest */
            HTTPParser parser=new HTTPParser(mess);
            System.out.print(parser.toHTTPString());
            
            /* open socket to server and make request 
             * 
             * IMPORTANT: changed host to google.com to prevent redirect loop, 
             * change back to 'parser.returnHost()' after tests*/
            //Socket s=new Socket(parser.returnHost(), 80);
            Socket s=new Socket("google.com", 80);
            BufferedWriter wt=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            wt.write(parser.toHTTPString());
            wt.flush();
            
            /* read response with ByteArrayOutputStream since it might contain binary strings */
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}