package my.egorrridze.trppconverter;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class MySoapService {

    @WebMethod
    @WebResult(name = "HelloMessage")
    public String Hello(String name){
        return "Hello, " + (name != null ? name : "noname") + "!";
    }

}
