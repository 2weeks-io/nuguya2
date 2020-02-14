package io.weeks.nuguya.Service;

import io.weeks.FileService.FileService;
import io.weeks.config.dto.FileConfigDto;
import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Repository.WritingDtlRepository;
import io.weeks.nuguya.Repository.WritingRepository;
import io.weeks.nuguya.dto.CrawlingDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
public class WritingService {

    @Autowired
    private FileConfigDto fileConfigDto;

    @Autowired
    private FileService fileService;

    @Autowired
    private CrawlingService crawlingService;

    @Autowired
    WritingRepository writingRepository;

    @Autowired
    WritingDtlRepository writingDtlRepository;

    public void insertWriting(Writing writing, CrawlingDto crawlingDto, MultipartHttpServletRequest multipartRequest) throws Exception{

        try {
            String appName = fileConfigDto.getApplicationName();
            String fileUploadPath = fileConfigDto.getFileUploadPath();
            String path = fileUploadPath + fileService.makeSaveFilePath(appName);

            String url = "http://localhost:5000/crawling/image/";
            String keywords = crawlingDto.getSrhchKeywords();
            String crawlingNum = Integer.toString(crawlingDto.getCrawlingNum());

            String requestUrl = url + "/" + appName + "/" + keywords + "/" + crawlingNum;
            crawlingDto.setRequestUrl(requestUrl);

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

                Writing newWriting = writingRepository.save(writing);

                //JSONObject 파싱 후 상세 데이터 저장
                JSONObject jsonObject = crawlingService.getCrawlingImg(crawlingDto);
                String[] keywordArray = crawlingDto.getSrhchKeywords().split(",", 0);
                int keywordLen = keywordArray.length;

                for(int j=0;j<keywordLen;j++) {

                    String keyword = keywordArray[j];

                    System.out.println("test");
                    //키워드별로 저장 위치 출력
                    JSONArray imageList = (JSONArray) jsonObject.get(keyword);
                    System.out.println(imageList.toString());
                    int imageLen = imageList.size();

                    for (int i = 0; i < imageLen; i++) {

                        WritingDtl writingDtl = new WritingDtl();
                        writingDtl.setRegpeId(writing.getRegpeId());
                        writingDtl.setModpeId(writing.getModpeId());
                        writingDtl.setAnswer(keyword);

                        //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                        Object fileInfoObj = (Object) imageList.get(i);

                        //JSON name으로 추출
                        System.out.println(fileInfoObj.toString());

                        //이미지 경로 저장
                        writingDtl.setOriImgPath1(fileInfoObj.toString());
                        writingDtl.setWriting(writing);
                        writingDtlRepository.save(writingDtl);

                    }
                }

            } else if ("20".equals(writing.getWritingDivCd())) { //눈코입 게임 등록

            }

            //List<Writing> writingList = writingRepository.findAll();

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

    public JSONObject getMainWriting() throws Exception{

        JSONObject jsonObject = new JSONObject();

        //List<Writing> writingList = writingRepository.findAll();

        Long tt = 20L;
        List<Writing> writingList = writingRepository.findAll();
        jsonObject.put("writingList", writingList);
        return jsonObject;
    }

}
