/**
 *
 * @author Devasia
 */
import java.util.*;

public class HTTPResponse {

    private HTTPParser parser;
    private byte[] binaryData;

    public HTTPResponse(String mess, byte[] b) {
        parser = new HTTPParser(mess);
        binaryData = b;
    }

    public HTTPParser returnParser() {
        return parser;
    }

    public byte[] toHTTPBytes() {
        byte[] one = parser.toHTTPString().getBytes();
        byte[] two = binaryData;
        byte[] combined = new byte[one.length + two.length];

        System.arraycopy(one, 0, combined, 0, one.length);
        System.arraycopy(two, 0, combined, one.length, two.length);
        
        return combined;
    }
}
