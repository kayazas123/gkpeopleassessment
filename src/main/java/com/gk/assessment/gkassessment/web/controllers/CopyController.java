package com.gk.assessment.gkassessment.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Controller
public class CopyController {

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "copy/about";
    }

}
