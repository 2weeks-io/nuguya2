package io.weeks.nuguya.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/getWritePage")
    public String getWritePage(Model model){

        return "writing";
    }

    @PostMapping("/write")
    public @ResponseBody String write(){

        return "hi";
    }

}
