package io.weeks.nuguya.Service;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingType;
import io.weeks.nuguya.Repository.WritingRepository;
import io.weeks.nuguya.Repository.WritingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WritingTypeService {

    @Autowired
    private WritingTypeRepository writingTypeRepository;

    @Autowired
    private WritingRepository writingRepository;

    /*
     ** 게임 유형 데이터 리스트 조회
     */
    public List<WritingType> getWritingTypes(){

        return writingTypeRepository.findAll();
    }

    /*
     ** 게임 유형 데이터 조회
     */
    public WritingType getWritingType(String writingDivCd){

        return writingTypeRepository.findByWritingDivCd(writingDivCd);
    }

    /*
     ** 메인페이지 - 게임 유형별 총 참여자 수
     */
    public int getSumParticiNum(WritingType writingType) throws Exception{

        String writingDivCd = writingType.getWritingDivCd();
        int sum = 0;
        List<Writing> writingList = writingRepository.findByWritingDivCd(writingDivCd);

        for(Writing writing : writingList){
            sum += writing.getParticiNum();
        }

        return sum;
    }

}
