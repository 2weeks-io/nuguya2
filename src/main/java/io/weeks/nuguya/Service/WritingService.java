package io.weeks.nuguya.Service;

import io.weeks.FileService.FileService;
import io.weeks.config.dto.FileConfigDto;
import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Repository.WritingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Date;
import java.util.List;

@Service
public class WritingService {

    @Autowired
    private FileConfigDto fileConfigDto;

    @Autowired
    private FileService fileService;

    @Autowired
    WritingRepository writingRepository;

    public void insertWriting(Writing writing, MultipartHttpServletRequest multipartRequest) throws Exception{

        try {
            String appName = fileConfigDto.getApplicationName();
            String fileUploadPath = fileConfigDto.getFileUploadPath();
            String path = fileUploadPath + fileService.makeSaveFilePath(appName);

            //제목 이미지 업로드 및 경로 세팅
            String titleImgPath = fileService.restore(multipartRequest.getFile("titleImg"), path);
            writing.setTitleImgPath(titleImgPath);


            List<MultipartFile> banImgFileList = multipartRequest.getFiles("banImg");
            if ("10".equals(writing.getWritingDivCd())) {  //모자이크 게임 등록

                for(int j=0;j<5;j++){

                    if("".equals(banImgFileList.get(j).getOriginalFilename())){
                        break;
                    } else{
                        String banImgPath   = fileService.restore(banImgFileList.get(j), path);
                        this.setBanImgPath(writing, banImgPath, j);
                    }
                }

            } else if ("20".equals(writing.getWritingDivCd())) { //눈코입 게임 등록

            }

            Writing newWriting = writingRepository.save(writing);

            //List<Writing> writingList = writingRepository.findAll();
            System.out.println(newWriting.toString());

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /*
     ** 인덱스에 따른 배너 이미지 경로 세팅
     */
    public void setBanImgPath(Writing writingDto, String banImgPath, int index) throws Exception{

        if(index == 0) {
            writingDto.setBanImgPath1(banImgPath);
        } else if(index == 1) {
            writingDto.setBanImgPath2(banImgPath);
        } else if(index == 2) {
            writingDto.setBanImgPath3(banImgPath);
        } else if(index == 3) {
            writingDto.setBanImgPath4(banImgPath);
        } else if(index == 4){
            writingDto.setBanImgPath5(banImgPath);
        }

    }

}
