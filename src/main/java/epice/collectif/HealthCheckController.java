package epice.collectif;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HealthCheckController {


    @RequestMapping(path = "healthcheck", method = GET)
    public boolean getHealthCheck(){

        return true;
    }
}
