package io.weeks.nuguya.Service;

import io.weeks.FileService.FileService;
import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Repository.WritingDtlRepository;
import io.weeks.nuguya.Repository.WritingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WritingMngService {

    @Autowired
    WritingDtlRepository writingDtlRepository;

    @Autowired
    WritingRepository writingRepository;

    @Autowired
    FileService fileService;

    @Value("${weeks.applicationName}")
    private String applicationName;

    @Value("${weeks.fileUploadPath}")
    private String fileUploadPath;

    public Writing getWritingDtl(Writing writing){
        writing = writingRepository.findByWritingNo(writing.getWritingNo());
        return writing;
    }

    public String deleteWritingDtl(WritingDtl writingDtl) throws Exception{

        String resultMsg = "success";

        try{

            String oriImgPath1 = writingDtl.getOriImgPath1();
            oriImgPath1 = oriImgPath1.replace("/assets", "");
            oriImgPath1 = oriImgPath1.replace("/" + applicationName, "");
            oriImgPath1 = fileUploadPath + oriImgPath1;

            String compoImgPath1 = writingDtl.getCompoImgPath1();

            //게시글 상세 삭제
            writingDtlRepository.deleteByWritingNoAndWritingSeq(writingDtl.getWritingNo(), writingDtl.getWritingSeq());

            //게시글 이미지 삭제
            fileService.deleteFile(oriImgPath1);

        } catch(RuntimeException e){
            resultMsg = "fail";
        }

        return resultMsg;

    }
}
