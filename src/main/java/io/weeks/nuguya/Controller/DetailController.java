package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Service.WritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DetailController {

    @Autowired
    private WritingService writingService;

    @GetMapping(value = "/writing/{writingNo}/{pageSize}", produces = "application/json; charset=UTF-8")
    public Map<String,Object> getWriting(Pageable pageable, @PathVariable Long writingNo, @PathVariable int pageSize) throws Exception{

        String resultMsg = "success";
        Map<String, Object> jsonObject =new HashMap<String, Object>();

        Writing writing = new Writing();
        writing.setWritingNo(writingNo);

        try {
            writing = writingService.getWriting(writing);

            double averageNum = (double)writing.getScore() / (double)writing.getParticiNum();
            averageNum = Math.round(averageNum*10)/10.0;
            writing.setAverageNum(averageNum);

            pageable = PageRequest.of(0, pageSize);

            //게시글 상세 내역 조회
            List<WritingDtl> writingDtlList = writingService.getRandomWritingDtl(writingNo, pageable);

            int randAnswerNum = 3; //보기개수
            writingDtlList = writingService.setRandomAnser(writing, writingDtlList, randAnswerNum);

            writing.setWritingDtlList(writingDtlList);
        } catch(Exception e){
            e.printStackTrace();
            resultMsg = "fail";
        }


        jsonObject.put("resultMsg", resultMsg);
        jsonObject.put("content", writing);

        return jsonObject;
    }

    @PostMapping(value = "/writing/{writingNo}/{score}", produces = "application/json; charset=UTF-8")
    public Map<String, Object> updateWritingScore(Writing writing){

        String resultMsg = "success";

        Map<String, Object> jsonObject =new HashMap<String, Object>();

        int curScore = writing.getScore();

        try {
            writing = writingService.getWriting(writing);

            int particiNum = writing.getParticiNum();
            particiNum += 1; //참여자수 1 증가

            writing.setParticiNum(particiNum);
            writing.setScore(curScore+writing.getScore());
            writing = writingService.updateWritingScore(writing);

            //평균값 세팅
            double averageNum = (double)writing.getScore() / (double)writing.getParticiNum();
            averageNum = Math.round(averageNum*10)/10.0;
            writing.setAverageNum(averageNum);

        } catch(Exception e){
            e.printStackTrace();
            resultMsg = "fail";
        }

        jsonObject.put("resultMsg", resultMsg);
        jsonObject.put("content", writing);

        return jsonObject;
    }

}
