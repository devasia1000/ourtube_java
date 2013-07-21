/**
 *
 * @author Devasia
 */

import java.io.*;
import java.net.*;

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
            //System.out.print(parser.toHTTPString());
            
            /* open socket to server and make request 
             * 
             * IMPORTANT: changed host to usatoday.com to prevent redirect loop, 
             * change back to 'parser.returnHost()' after tests*/
            //Socket s=new Socket(parser.returnHost(), 80);
            Socket s = new Socket("usatoday.com", 80);
            BufferedWriter wt = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            wt.write(parser.toHTTPString());
            wt.flush();

            /* read reponse headers with BufferedReader and binary content with InputStream */
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String line2 = null, mess2 = "";
            while ((line2 = serverReader.readLine()) != null) {
                if (line2.equals("")) {
                    /* finished reading reponse headers */
                    System.out.println("breaking");
                    break;
                } else {
                    mess2 = mess2 + line2 + "\n";
                }
            }

            System.out.println("outside loop");
            /* parse response headers to find 'Content-Length' */
            HTTPParser p = new HTTPParser(mess2);
            String length = p.returnHeaderValue("Content-Length");
            int contentLength = Integer.parseInt(length);

            System.out.println("content-length: "+contentLength);
            
            /* read binary content */
            InputStream binaryReader = s.getInputStream();
            
            System.out.println("retrieved input stream");
            
            byte[] b = new byte[contentLength];
            
            /* ERROR: This function blocks forever!! */
            binaryReader.read(b);
            
            System.out.println("read binary data: "+new String(b));

            HTTPResponse response = new HTTPResponse(mess2, b);
            byte[] totalData = response.toHTTPBytes();

            /* write response to client */
            DataOutputStream os = new DataOutputStream(sock.getOutputStream());
            os.write(totalData);
            os.flush();
            
            System.out.println(new String(totalData));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}