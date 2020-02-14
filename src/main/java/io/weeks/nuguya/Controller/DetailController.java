package io.weeks.nuguya.Controller;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Service.WritingService;
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
public class DetailController {

    @Autowired
    private WritingService writingService;

    @GetMapping("/writing/{writingNo}")
    public Map<String,Object> getWriting(Pageable pageable, @PathVariable Long writingNo) throws Exception{

        String resultMsg = "success";

        Writing writing = new Writing();
        writing.setWritingNo(writingNo);

        writing = writingService.getWriting(writing);

        /*
        WritingDtl writingDtl = new WritingDtl();
        writingDtl.setWriting(writing);

        Page<WritingDtl> writingDtls = writingService.getWritingDtl(pageable, writingDtl);
        */

        Map<String, Object> jsonObject =new HashMap<String, Object>();

        jsonObject.put("resultMsg", resultMsg);
        jsonObject.put("content", writing);

        return jsonObject;
    }

}
