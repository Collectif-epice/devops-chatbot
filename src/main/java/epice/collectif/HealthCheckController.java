package epice.collectif;

import epice.collectif.repository.InternalServerErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HealthCheckController extends CustomExceptionHandler {

    @Autowired
    private Chatbot chatbot;

    @Autowired
    private InternalServerErrorRepository internalServerErrorRepo;

    @RequestMapping(path = "healthcheck", method = GET)
    public ResponseEntity<String> getHealthCheck(){

       if(!chatbot.getSession().isConnected()){
           return status(INTERNAL_SERVER_ERROR).body("Couldn't create the slack session");
       }
       else if(internalServerErrorRepo.getSessionCount() > 10){
            return status(INTERNAL_SERVER_ERROR).body("More than 10 http 500 error");
       }
       else {
           return ok("RAS");
       }
    }
}
