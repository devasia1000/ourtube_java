
/**
 *
 * @author Devasia
 */
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class MultiThreadedServer implements Runnable {

    Socket sock;

    MultiThreadedServer(Socket csocket) {
        this.sock = csocket;
    }

    @Override
    public void run() {
        try {

            /* keep these two streams open */
            InputStream rd = sock.getInputStream();
            DataOutputStream os = new DataOutputStream(sock.getOutputStream());

            //while (true) {
            byte[] binaryRequest = new byte[1048576];
            //System.out.println("going to read");
            int numBytesRead = rd.read(binaryRequest);
            if (numBytesRead == 0 || numBytesRead == -1) {
                System.err.println("could not read request");
                throw new Exception();
            }
            //System.out.println(numBytesRead);
            byte[] requestBinary_small = Arrays.copyOfRange(binaryRequest, 0, numBytesRead);
            String mess = new String(requestBinary_small);
            mess = mess.replaceAll("\r\n", "\n");
            //System.out.print(mess);

            /* parse HTTPRequest */
            HTTPRequest request = new HTTPRequest(mess);

            /* open socket to server and make request 
             * 
             * IMPORTANT: changed host to usatoday.com to prevent redirect loop, 
             * change back to 'parser.returnHost()' after tests*/
            Socket s = new Socket(request.returnParser().returnHost(), 80);
            //Socket s = new Socket("usatoday.com", 80);
            BufferedWriter wt = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            wt.write(request.returnParser().toHTTPString());
            wt.flush();

            /* read response as a stream of bytes with InputStream */
            InputStream binaryReader = s.getInputStream();
            /* we're using a 1MB byte array to store data...will that be enough? what is we need to store more data? */
            byte[] data = new byte[1048576];
            int dataRead = binaryReader.read(data);
            /* throw an exception if no response is read from server */
            if (dataRead == 0 || dataRead == -1) {
                System.err.println("could not read any data from server");
                throw new Exception();
            }

            /* move binary data into smaller byte array */
            byte[] data_small = Arrays.copyOfRange(data, 0, dataRead);
            /* mark larger byte array for garbage collection */
            data = null;

            System.out.println("read " + dataRead + " bytes from server: " + new String(data_small));

            /* close socket to server */
            s.close();

            /* parse HTTP response and store in an object */
            HTTPResponse response = new HTTPResponse(data_small);
            byte[] totalData = response.toHTTPBytes();

            /* write response to client */
            os.write(totalData);
            os.flush();

            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}