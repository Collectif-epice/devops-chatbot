package epice.collectif;

import epice.collectif.repository.InternalServerErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by anthonyrey on 01/12/2016.
 */

@Controller
public class CustomExceptionHandler {

    @Autowired
    private InternalServerErrorRepository internalServerErrorRepo;

    @ExceptionHandler(Exception.class)
    public void handle(){

        internalServerErrorRepo.increment();
    }
}
