/**
 *
 * @author Devasia
 */

import java.util.*;

public class HTTPParser {
    
    private String header_line;
    private ArrayList<HTTPHeader> headerList=new ArrayList<HTTPHeader>();

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
        String url="";
        
        for(HTTPHeader head : headerList){
            if(head.returnHeaderName().toLowerCase().equals("host")){
                url=url+head.returnHeaderValue();
            }
        }
        
        String[] temp=header_line.split(" ");
        String uri=temp[1];
        
        url=url+uri;
        
        return url;
    }
    
    public String toHTTPString(){
        String mess="";
        mess=mess+header_line+"\r\n";
        for(HTTPHeader head : headerList){
            mess=mess+head.returnHeaderName()+" : "+head.returnHeaderValue()+"\r\n";
        }
        mess=mess+"\r\n";
        return mess;
    }
}
