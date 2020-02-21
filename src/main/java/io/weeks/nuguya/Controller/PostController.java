package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Service.WritingService;
import io.weeks.nuguya.dto.CrawlingDto;
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
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private WritingService writingService;

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);

    /*
     ** 게시글 작성 페이지 이동
     */
    @GetMapping(value = "/page/writing")
    public String getWritePage(Model model) {

        return "writing";
    }

    /*
     ** 게시글 작성
     */
    @PostMapping(value = "/write")
    public String write(HttpServletRequest request, HttpSession session, Writing writing, CrawlingDto crawlingDto) throws Exception{

        writing.setRegpeId(session.toString());
        writing.setModpeId(session.toString());
        writing.setUseYn("Y");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

        writingService.insertWriting(writing, crawlingDto, multipartRequest);

        return "index";
    }

}
