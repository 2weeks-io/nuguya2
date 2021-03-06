package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Service.WritingService;
import io.weeks.nuguya.dto.CrawlingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    public @ResponseBody Map<String,Object> write(HttpServletRequest request, HttpSession session, Writing writing, CrawlingDto crawlingDto) throws Exception{

        String resultMsg = "success";
        Map<String, Object> resultMap =new HashMap<String, Object>();

        writing.setRegpeId(session.toString());
        writing.setModpeId(session.toString());
        writing.setUseYn("Y");
        String title = writing.getTitle();
        title = title.replace(",", "");
        writing.setTitle(title);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

        resultMsg = writingService.insertWriting(writing, crawlingDto, multipartRequest);
        resultMap.put("resultMsg", resultMsg);

        return resultMap;
    }

    /*
     ** 게시글 상세 데이터 추가
     */
    @PostMapping(value = "/write/detail")
    public @ResponseBody Map<String, Object> updateWrite(HttpServletRequest request, HttpSession session, Writing writing) throws Exception{

        writing.setRegpeId(session.toString());
        writing.setModpeId(session.toString());
        writing.setUseYn("Y");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;

        String resultMsg = writingService.updateWriting(writing, multipartRequest);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("resultMsg", resultMsg);
        resultMap.put("writing", writing);

        return resultMap;
    }

    /*
     ** 공유 횟수 업데이트
     */
    @PutMapping(value = "/writing/{writingNo}/{shareDivCd}", produces = "application/json; charset=UTF-8")
    public @ResponseBody Map<String, Object> updateShareNum(Writing writing) throws Exception{

        Map<String, Object> resultMap = writingService.updateShareNum(writing);

        return resultMap;
    }

}
