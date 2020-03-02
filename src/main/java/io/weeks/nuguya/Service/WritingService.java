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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;

@Service
@Transactional
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

    @Value("${weeks.crawlingServer.url}")
    private String crawlingUrl;

    /*
     ** 게임 게시글 업로드
     */
    public void insertWriting(Writing writing, CrawlingDto crawlingDto, MultipartHttpServletRequest multipartRequest) throws Exception{

        try {
            String appName        = fileConfigDto.getApplicationName();
            String fileUploadPath = fileConfigDto.getFileUploadPath(); //기본 파일 업로드 경로
            String saveFilePath   = fileService.makeSaveFilePath();   //날짜에 따른 폴더 경로
            String path           = fileUploadPath + saveFilePath;
            String srchPrefix    = writing.getSrchPrefix();

            //제목 이미지 업로드 및 경로 세팅
            String titleImgPath = "/assets/" + appName + saveFilePath + fileService.restore(multipartRequest.getFile("titleImg"), path);
            writing.setTitleImgPath(titleImgPath);

            if ("10".equals(writing.getWritingDivCd())) {  //모자이크 게임 등록

                //크롤링 정보 입력
                String keywords    = crawlingDto.getSrhchKeywords();
                String crawlingNum = Long.toString(crawlingDto.getCrawlingNum());
                String requestUrl  = crawlingUrl + "/" + appName + "/" + srchPrefix + "/" + keywords + "/" + crawlingNum;
                crawlingDto.setRequestUrl(requestUrl);

                //게시글 등록
                Writing newWriting = writingRepository.save(writing);

                //JSONObject 파싱 후 상세 데이터 저장
                JSONObject jsonObject = crawlingService.getCrawlingImg(crawlingDto);
                String[] keywordArray = crawlingDto.getSrhchKeywords().split(",", 0);
                int keywordLen = keywordArray.length;

                for(int j=0;j<keywordLen;j++) {

                    String keyword = keywordArray[j];

                    System.out.println("keyword : " + keyword);
                    //키워드별로 저장 위치 출력
                    JSONArray imageList = (JSONArray) jsonObject.get(keyword);

                    if(imageList == null){
                        System.out.println("imageList is null");
                        continue;
                    }

                    int imageLen = imageList.size();
                    Long writingSeq = 0L;

                    for (int i = 0; i < imageLen; i++) {

                        WritingDtl writingDtl = new WritingDtl();
                        writingDtl.setRegpeId(writing.getRegpeId());
                        writingDtl.setModpeId(writing.getModpeId());
                        writingDtl.setAnswer(keyword);
                        writingDtl.setWritingNo(newWriting.getWritingNo());

                        //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                        Object fileInfoObj = (Object) imageList.get(i);

                        //JSON name으로 추출
                        System.out.println(fileInfoObj.toString());

                        //이미지 경로 저장
                        String resourcePath = "/assets/nuguya" + fileInfoObj.toString();

                        writingDtl.setOriImgPath1(resourcePath);
                        writingDtl.setWriting(writing);
                        writingDtlRepository.save(writingDtl);
                    }
                }

            } else if ("20".equals(writing.getWritingDivCd())) { //눈코입 게임 등록

                //게시글 등록
                Writing newWriting = writingRepository.save(writing);

                List<MultipartFile> oriImgFile   = multipartRequest.getFiles("oriImgFile");
                List<MultipartFile> compoImgFile = multipartRequest.getFiles("compoImgFile");

                int oriImgFileSize   = oriImgFile.size();
                int compoImgFileSize = compoImgFile.size();

                int oriLen   = 0;
                int compoLen = 0;

                //원본 합성이미지 모두 입력되있다고 가정
                for(int j=0;j<oriImgFileSize;j++) {
                    if(!oriImgFile.get(j).isEmpty()) {
                        oriLen++;
                    } else {
                        break;
                    }
                }

                for(int i=1;i<=oriLen;i++) {
                    String oriImgPath1   = "/assets/" + appName + saveFilePath + fileService.restore(oriImgFile.get(i-1), path);
                    String compoImgPath1 = "/assets/" + appName + saveFilePath + fileService.restore(compoImgFile.get(i-1), path);
                    String answer        = writing.getAnswer().get(i-1);
                    WritingDtl writingDtl = new WritingDtl();
                    writingDtl.setRegpeId(writing.getRegpeId());
                    writingDtl.setModpeId(writing.getModpeId());
                    writingDtl.setAnswer(answer);
                    writingDtl.setWritingNo(newWriting.getWritingNo());

                    //이미지 경로 저장
                    writingDtl.setOriImgPath1(oriImgPath1);
                    writingDtl.setCompoImgPath1(compoImgPath1);
                    writingDtl.setWriting(writing);
                    writingDtlRepository.save(writingDtl);
                }

            }

            //List<Writing> writingList = writingRepository.findAll();

        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    /*
     ** 카테고리 게시글 리스트 조회
     */
    public Page<Writing> getMainWriting(Pageable pageable, String writingDivCd) throws Exception{

        String useYn = "Y";

        return writingRepository.findByWritingDivCdAndUseYn(pageable, writingDivCd, useYn);
    }

    /*
     ** 게시글 조회
     */
    public Writing getWriting(Writing writing) throws Exception{

        Long writingNo = writing.getWritingNo();

        return writingRepository.findByWritingNo(writingNo);
    }

    /*
     ** 게시글 상세 랜덤 조회
     */
    public List<WritingDtl> getRandomWritingDtl(Long writingNo, Pageable pageable) throws Exception{

        return writingDtlRepository.findByRandomWritingNo(writingNo, pageable).getContent();
    }

    /*
     ** 게시글 상세 랜덤 보기 세팅
     */
    public List<WritingDtl> setRandomAnser(Writing writing, List<WritingDtl> writingDtlList, int randAnswerNum) throws Exception{

        List<WritingDtl> totalWritingDtlList = writing.getWritingDtlList();
        List<String> randAnswerArr = new ArrayList<String>();

        int totalLen = totalWritingDtlList.size();

        //랜덤 보기 리스트 구성
        for(int i = 0; i<totalLen ; i++){
            if(!randAnswerArr.contains(totalWritingDtlList.get(i).getAnswer())){
                randAnswerArr.add(totalWritingDtlList.get(i).getAnswer());
            }
        }

        int max = randAnswerArr.size()-1;

        //게시글 마다 랜덤 보기 생성
        for(WritingDtl w : writingDtlList){

            //게시글 상세에 세팅해줄 랜덤 보기
            String randAnswer = "";
            int cnt = 0;

            List<String> randList = new ArrayList<String>();

            while(cnt<randAnswerNum-1) {

                int randomNum = randomRange(0, max);

                String curRandomAnswer = randAnswerArr.get(randomNum);
                if(!w.getAnswer().equals(curRandomAnswer) && !randList.contains(curRandomAnswer)){  //현재 정답과 랜덤 보기가 달라야 넣음
                    randList.add(curRandomAnswer);
                    cnt++;
                }

                //마지막에 정답을 넣어줌
                if(cnt == randAnswerNum-1) {
                    randList.add(w.getAnswer());
                    w.setRandAnswer(randAnswer);
                }
            }

            //리스트 셔플
            Collections.shuffle(randList);

            randAnswer ="";

            for(String answer : randList){
                randAnswer += answer + ",";
            }

            //마지막 , 제거
            randAnswer = randAnswer.substring(0,randAnswer.length()-1);

            w.setRandAnswer(randAnswer);

        }

        return writingDtlList;
    }

    /*
     ** min ~ max 까지 random 정수 출력
     */
    public int randomRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public Writing updateWritingScore(Writing writing){

        writingRepository.save(writing);

        return writing;
    }

    /*
     ** 공유하기 해쉬태그 멤버 리스트 구성
     */
    public List<String> getMembers(int num, List<WritingDtl> writingDtlList){

        List<String> members = new ArrayList<String>();
        int cnt = 0;

        for(WritingDtl writingDtl : writingDtlList){
            String memberName = writingDtl.getAnswer();
            if(cnt < num) {
                break;
            } else if(!members.contains(memberName)){
                    members.add(memberName);
                    cnt++;
                }
            }

        return members;
    }

    /*
     ** 공유하기 횟수 업데이트
     */
    public Map<String, Object> updateShareNum(Writing writing) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Writing updateWriting = new Writing();
        String resultMsg = "success";

        try {
            Writing curWriting = writingRepository.findByWritingNo(writing.getWritingNo());

            String shareDivCd = writing.getShareDivCd();
            int shareNum = 0;
            if("10".equals(shareDivCd)){   //카카오 공유 횟수 업데이트
                shareNum = curWriting.getKakaoShareNum() + 1;
                curWriting.setKakaoShareNum(shareNum);
            } else if("20".equals(shareDivCd)){ //페이스북 공유 횟수 업데이트
                shareNum = curWriting.getFacebookShareNum() + 1;
                curWriting.setFacebookShareNum(shareNum);
            }

            updateWriting = writingRepository.save(curWriting);

        } catch(Exception e){
            resultMsg = "fail";
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        resultMap.put("resultMsg", resultMsg);
        resultMap.put("content", updateWriting);

        return resultMap;
    }

    public Map<String, Object> updateWriting(Writing writing, MultipartHttpServletRequest multipartRequest) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try{

            if ("10".equals(writing.getWritingDivCd())) {  //모자이크 게임 추가 등록

                String appName        = fileConfigDto.getApplicationName();
                String fileUploadPath = fileConfigDto.getFileUploadPath(); //기본 파일 업로드 경로
                String saveFilePath   = fileService.makeSaveFilePath();   //날짜에 따른 폴더 경로
                String path           = fileUploadPath + saveFilePath;

                List<MultipartFile> oriImgFile   = multipartRequest.getFiles("oriImgFile");

                int oriImgFileSize   = oriImgFile.size();

                int oriLen   = 0;

                //원본 이미지 개수
                for(int j=0;j<oriImgFileSize;j++) {
                    if(!oriImgFile.get(j).isEmpty()) {
                        oriLen++;
                    } else {
                        break;
                    }
                }

                //이미지 등록
                for(int i=1;i<=oriLen;i++) {
                    String oriImgPath1   = "/assets/" + appName + saveFilePath + fileService.restore(oriImgFile.get(i-1), path);
                    String answer        = writing.getAnswer().get(i-1);
                    WritingDtl writingDtl = new WritingDtl();
                    writingDtl.setRegpeId(writing.getRegpeId());
                    writingDtl.setModpeId(writing.getModpeId());
                    writingDtl.setAnswer(answer);
                    writingDtl.setWritingNo(writing.getWritingNo());

                    //이미지 경로 저장
                    writingDtl.setOriImgPath1(oriImgPath1);
                    writingDtl.setWriting(writing);
                    writingDtlRepository.save(writingDtl);
                }

            } else if ("20".equals(writing.getWritingDivCd())) { //눈코입 게임 추가 등록

            }

        } catch(Exception e){
            throw new RuntimeException(e);
        }

        return resultMap;
    }
}
