package epice.collectif;

import epice.collectif.repository.InternalServerErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by anthonyrey on 23/11/2016.
 */
@RestController
public class ErrorCountController extends CustomExceptionHandler {

    @Autowired
    private InternalServerErrorRepository internalServerErrorRepo;

    @RequestMapping(method= GET, value = "/incrementErrorCount")
    public int inc(){

        internalServerErrorRepo.increment();
        return internalServerErrorRepo.getSessionCount();
    }

    @RequestMapping(method=GET, value= "/errors")
    public int getErrorCount(){

        return internalServerErrorRepo.getSessionCount();
    }

    @RequestMapping(method=GET, value= "/generateHttp500")
    public void generateExc() throws Exception {

        throw new Exception();
    }

}
