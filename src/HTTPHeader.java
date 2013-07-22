
/**
 *
 * @author Devasia
 */
public class HTTPHeader {

    private String headerName;
    private String value;
    
    HTTPHeader(String head){
        /* only split on first colon */
        String[] temp=head.split(":");
        headerName=temp[0].trim();
        value="";
        for(int i=1;i<temp.length;i++){
            value=value+temp[i];
        }
    }
    
    String returnHeaderName(){
        return headerName;
    }
    
    String returnHeaderValue(){
        return value;
    }
}
