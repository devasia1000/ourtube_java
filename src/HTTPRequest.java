

/**
 *
 * @author Devasia
 */

public class HTTPRequest {
    
    private HTTPParser parser;
    
    public HTTPRequest(String req){
        parser=new HTTPParser(req);
    }
    
    public HTTPParser returnParser(){
        return parser;
    }
}
