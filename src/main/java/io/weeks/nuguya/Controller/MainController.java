package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Repository.WritingRepository;
import io.weeks.nuguya.Service.WritingService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private WritingService writingService;

    @GetMapping(value ="/writings/{pageNum}/{pageSize}", produces = "application/json; charset=UTF-8")
    public Map<String,Object> getPosts(Pageable pageable, @PathVariable Integer pageNum, @PathVariable Integer pageSize) throws Exception{

        String resultMsg = "success";

        pageable = PageRequest.of(pageNum, pageSize);

        Page<Writing>  writingList = writingService.getMainWriting(pageable);

        Map<String, Object> jsonObject =new HashMap<String, Object>();

        jsonObject.put("resultMsg", resultMsg);
        jsonObject.put("content", writingList);

        return jsonObject;
    }

}
