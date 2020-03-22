package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Service.WritingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DetailController {

    @Autowired
    private WritingService writingService;

    /*
     ** 게임글 상세 조회
     */
    @GetMapping(value = "/writing/{writingNo}/{pageSize}", produces = "application/json; charset=UTF-8")
    public Map<String,Object> getWriting(Pageable pageable, @PathVariable Long writingNo, @PathVariable int pageSize) throws Exception{

        String resultMsg = "success";
        Map<String, Object> resultMap =new HashMap<String, Object>();

        Writing writing = new Writing();
        writing.setWritingNo(writingNo);

        try {
            writing = writingService.getWriting(writing);

            double particiNum = (double)writing.getParticiNum();

            if(particiNum == 0){
                particiNum = 1;
            }

            double averageNum = (double)writing.getScore() / particiNum;
            averageNum = Math.round(averageNum*10)/10.0;
            writing.setAverageNum(averageNum);



            String writingDtlCd = writing.getWritingDtlCd();

            //게시글 상세 내역 조회
            List<WritingDtl> writingDtlList = new ArrayList<WritingDtl>();

            if("10".equals(writingDtlCd)) {
                pageable = PageRequest.of(0, pageSize);

                writingDtlList = writingService.getRandomWritingDtl(writingNo, pageable);

                int randAnswerNum = 3; //보기개수
                writingDtlList = writingService.setRandomAnser(writing, writingDtlList, randAnswerNum);

            } else if("20".equals(writingDtlCd)){
                pageable = PageRequest.of(0, pageSize, Sort.by("regDts").ascending());

                writingDtlList = writingService.getWritingList(writing, pageable);

                //보기 셔플
                for(WritingDtl writingDtl : writingDtlList){
                    List<String> randAnswerList = new ArrayList<String>();
                    randAnswerList.add(writingDtl.getAnswer());
                    randAnswerList.add(writingDtl.getExample1());
                    randAnswerList.add(writingDtl.getExample2());
                    Collections.shuffle(randAnswerList);
                    writingDtl.setRandAnswer(randAnswerList);
                }

            }

            writing.setWritingDtlList(writingDtlList);
        } catch(Exception e){
            e.printStackTrace();
            resultMsg = "fail";
        }

        resultMap.put("content", writing);
        resultMap.put("resultMsg", resultMsg);

        return resultMap;
    }

    /*
     ** 게임 총 점수 업데이트
     */
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

        jsonObject.put("content", writing);
        jsonObject.put("resultMsg", resultMsg);

        return jsonObject;
    }

}
