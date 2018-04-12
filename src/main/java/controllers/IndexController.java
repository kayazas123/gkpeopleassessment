package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String home(){
	return "index";
    }

}
