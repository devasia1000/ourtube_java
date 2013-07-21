/**
 *
 * @author Devasia
 */

import java.util.*;

public class HTTPParser {
    
    String header_line;
    ArrayList<HTTPHeader> headerList=new ArrayList<HTTPHeader>();

    public HTTPParser(String mess) {
        String[] sp=mess.split("\r\n");
        header_line=sp[0];
        
        for(int i=1;i<sp.length-1;i++){
            HTTPHeader head=new HTTPHeader(sp[i]);
        }
    }
    
    public String returnHeaderLine(){
        return header_line;
    }
    
    public ArrayList<HTTPHeader> returnHeaders(){
        return null;
    }
    
    public String returnURL(){
        return null;
    }
}
