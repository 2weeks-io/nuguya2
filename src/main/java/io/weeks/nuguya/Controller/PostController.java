package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Service.WritingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    @Autowired
    private WritingService writingService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    @GetMapping("/page/writing")
    public String getWritePage(Model model) {

        return "writing";
    }

    @GetMapping("/test/http")
    public @ResponseBody String httpTest(){

        return "test";
    }

    @PostMapping("/write")
    public @ResponseBody String write(HttpServletRequest request, HttpSession session, Writing writing) throws Exception{

        writing.setRegpeId(session.toString());
        writing.setModpeId(session.toString());
        writing.setUseYn("Y");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

        writingService.insertWriting(writing, multipartRequest);

        return "hi";
    }

}
