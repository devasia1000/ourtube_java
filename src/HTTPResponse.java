/**
 *
 * @author Devasia
 */

import java.util.*;

public class HTTPResponse {

    ArrayList<HTTPHeader> headerList=new ArrayList<HTTPHeader>();
    
    public HTTPResponse(byte[] b){
        for(int i=0; i<b.length; i++){
            
            /* this if statement finds out where the headers end and where the content starts, its a
             * is a bit weird, so I'll try my best to explain it here:
             * OK, so we know the headers are over if we encounter a CLRF ('\r\n') twice in a row 
             * (one CLRF to end the previous header and one CLRF to start the content),
             * '13' is the ASCII code for '\r' and 10 is the ASCII code for '\n'
             */
            if(b[i]==13 && b[i+1]==10 && b[i+2]==13 && b[i+3]==10){
                System.out.println("Content starts at "+i+4);
            }
        }
    }
    
    
}
