package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Service.WritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DetailController {

    @Autowired
    private WritingService writingService;

    @GetMapping("/writing/{writingNo}/{pageSize}")
    public Map<String,Object> getWriting(Pageable pageable, @PathVariable Long writingNo, @PathVariable int pageSize) throws Exception{

        String resultMsg = "success";

        Writing writing = new Writing();
        writing.setWritingNo(writingNo);

        writing = writingService.getWriting(writing);

        pageable = PageRequest.of(0, pageSize);

        //게시글 상세 내역 조회
        List<WritingDtl> writingDtlList = writingService.getRandomWritingDtl(writingNo, pageable);

        int randAnswerNum = 3; //보기개수
        writingDtlList = writingService.setRandomAnser(writing, writingDtlList, randAnswerNum);

        writing.setWritingDtlList(writingDtlList);

        Map<String, Object> jsonObject =new HashMap<String, Object>();

        jsonObject.put("resultMsg", resultMsg);
        jsonObject.put("content", writing);

        return jsonObject;
    }

}
