package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AYAZ on 11/04/2018.
 */
@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public String sayHello(){
	return "index";
    }

}
