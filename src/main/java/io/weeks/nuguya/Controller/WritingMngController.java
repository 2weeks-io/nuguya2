package io.weeks.nuguya.Controller;

import io.weeks.FileService.FileService;
import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Service.WritingMngService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WritingMngController {

    @Autowired
    WritingMngService writingMngService;

    @Autowired
    FileService fileService;

    @Value("${weeks.reqServerUrl}")
    private String reqServerUrl;

    /*
     ** 게시글 상세 업데이트 페이지 반환
     */
    @GetMapping(value = "/page/writingUpdate/{writingNo}")
    public String getWritingUpdate(Model model, Writing writing) throws Exception{

        writing = writingMngService.getWritingDtl(writing);

        List<WritingDtl> writingDtlList = writing.getWritingDtlList();

        model.addAttribute("writing", writing);
        model.addAttribute("writingDtlList", writingDtlList);
        model.addAttribute("reqServerUrl", reqServerUrl);

        return "writingUpdate";
    }

    /*
     ** 게시글 상세 삭제
     */
    @DeleteMapping(value = "/writingDtl/{writingNo}/{writingSeq}")
    public @ResponseBody Map<String,Object> deleteWritingDtl(WritingDtl writingDtl) throws Exception{
        String resultMsg = "";
        Map<String, Object> jsonObject =new HashMap<String, Object>();

        resultMsg = writingMngService.deleteWritingDtl(writingDtl);

        jsonObject.put("resultMsg", resultMsg);
        jsonObject.put("writingDtl", writingDtl);

        return jsonObject;
    }

}
