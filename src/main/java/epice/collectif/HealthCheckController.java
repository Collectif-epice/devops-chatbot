package epice.collectif;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HealthCheckController {

    @Autowired
    private Chatbot chatbot;

    @RequestMapping(path = "healthcheck", method = GET)
    public ResponseEntity<String> getHealthCheck(){

       if(!chatbot.getSession().isConnected()){
           return status(INTERNAL_SERVER_ERROR).body("Couldn't create the slack session");
       }
       else {
           return ok("RAS");
       }
    }
}
