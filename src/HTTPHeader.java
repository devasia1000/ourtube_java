
/**
 *
 * @author Devasia
 */
public class HTTPHeader {

    private String headerName;
    private String value;
    
    HTTPHeader(String head){
        String[] temp=head.split(":");
        headerName=temp[0].trim();
        value=temp[1].trim();
    }
    
    String returnHeaderName(){
        return headerName;
    }
    
    String returnHeaderValue(){
        return value;
    }
}
