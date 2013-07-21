/**
 *
 * @author Devasia
 */

import java.util.*;

public class HTTPParser {
    
    String header_line;
    ArrayList<HTTPHeader> headerList=new ArrayList<HTTPHeader>();

    public HTTPParser(String mess) {
        String[] sp=mess.split("\n");
        header_line=sp[0];
        
        for(int i=1;i<sp.length;i++){
            HTTPHeader head=new HTTPHeader(sp[i]);
            headerList.add(head);
        }
    }
    
    public String returnHeaderLine(){
        return header_line;
    }
    
    public ArrayList<HTTPHeader> returnHeaders(){
        return headerList;
    }
    
    public String returnURL(){
        String[] temp=header_line.split(" ");
        return temp[1];
    }
}
