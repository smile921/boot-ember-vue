package org.smile921.app.ember.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {


    private Logger LOG = Logger.getLogger(getClass());

//    @RequestMapping({"/","/index",""})
//    public String index(){
//        LOG.debug("forward to index.html");
//        return "forward:/index.html";
//    }
}
