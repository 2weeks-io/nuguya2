package io.weeks.nuguya.Service;

import io.weeks.FileService.FileService;
import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Repository.WritingDtlRepository;
import io.weeks.nuguya.Repository.WritingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

        writingDtl = writingDtlRepository.findByWritingNoAndWritingSeq(writingDtl.getWritingNo(), writingDtl.getWritingSeq());

        try{

            String oriImgPath1 = writingDtl.getOriImgPath1();
            oriImgPath1 = oriImgPath1.replace("/assets", "");
            oriImgPath1 = oriImgPath1.replace("/" + applicationName, "");
            oriImgPath1 = fileUploadPath + oriImgPath1;

            String compoImgPath1 = writingDtl.getCompoImgPath1();

            //게시글 상세 삭제
            writingDtlRepository.deleteByWritingNoAndWritingSeq(writingDtl.getWritingNo(), writingDtl.getWritingSeq());

            //게시글 이미지 삭제
            if(!"success".equals(fileService.deleteFile(oriImgPath1))){
                throw new Exception();
            }

        } catch(Exception e){
            e.printStackTrace();
            resultMsg = "fail";
            throw new RuntimeException(e);
        }

        return resultMsg;

    }
}
