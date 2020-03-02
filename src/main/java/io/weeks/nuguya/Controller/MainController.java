package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingType;
import io.weeks.nuguya.Service.WritingService;
import io.weeks.nuguya.Service.WritingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private WritingService writingService;

    @Autowired
    private WritingTypeService writingTypeService;

    @GetMapping("test")
    public String getWritePage(Model model) {

        return "소스변경 반영 테스트!!!!";
    }

    /*
     ** 게임 유형 리스트 조회 (메인페이지)
     */
    @GetMapping(value ="/writingTypes", produces = "application/json; charset=UTF-8")
    public Map<String,Object> getWritingTypes() throws Exception{

        String resultMsg = "success";

        List<WritingType> writingTypeList = writingTypeService.getWritingTypes();

        for(WritingType writingType : writingTypeList){
            int sumParticiNum = writingTypeService.getSumParticiNum(writingType);
            writingType.setParticiNum(sumParticiNum);
        }

        Map<String, Object> resultMap =new HashMap<String, Object>();

        resultMap.put("content", writingTypeList);
        resultMap.put("resultMsg", resultMsg);

        return resultMap;
    }

    /*
     ** 게임 유형 조회
     */
    @GetMapping(value ="/writingType/{writingDivCd}", produces = "application/json; charset=UTF-8")
    public Map<String,Object> getWritingType(@PathVariable String writingDivCd) throws Exception{

        String resultMsg = "success";

        WritingType writingType = new WritingType();
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            writingType = writingTypeService.getWritingType(writingDivCd);
            resultMap.put("content", writingType);
            resultMap.put("resultMsg", resultMsg);
        } catch(RuntimeException e){
            resultMsg = "fail";
            resultMap.put("resultMsg", resultMsg);
        }

        return resultMap;
    }

    /*
     ** 게임 리스트 조회 (카테고리 페이지)
     */
    @GetMapping(value ="/writings/{writingDivCd}/{pageNum}/{pageSize}", produces = "application/json; charset=UTF-8")
    public Map<String,Object> getWritings(Pageable pageable, @PathVariable Integer pageNum, @PathVariable Integer pageSize, @PathVariable String writingDivCd) throws Exception{

        String resultMsg = "success";

        pageable = PageRequest.of(pageNum, pageSize, Sort.by("regDts").descending());

        Page<Writing>  writingList = writingService.getMainWriting(pageable, writingDivCd);

        Map<String, Object> resultMap =new HashMap<String, Object>();

        resultMap.put("content", writingList);
        resultMap.put("resultMsg", resultMsg);

        return resultMap;
    }

}
