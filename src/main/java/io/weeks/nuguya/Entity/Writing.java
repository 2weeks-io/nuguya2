package io.weeks.nuguya.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.weeks.dto.BaseTimeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@ToString(exclude = "writingDtlList")
@Table(name = "writing")
public class Writing extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long writingNo;

    private String writingDivCd;

    private String writingDtlCd;

    private String title;

    private String titleImgPath;

    private String main_ctg;

    private String useYn;

    @Transient
    private double averageNum;

    private int score;

    private int particiNum;

    private int kakaoShareNum;    //카카오톡 공유 횟수

    private int facebookShareNum; //페이스북 공유 횟수

    private String regpeId;

    private String modpeId;

    @Transient
    private String srchPrefix; //검색 Prefix

    @Transient
    private String solvedNum;

    @Transient
    private List<String> answer;

    @Transient
    private List<String> example1;

    @Transient
    private List<String> example2;

    @Transient
    private String shareDivCd;

    @OneToMany(mappedBy="writing")
    @JsonManagedReference
    private List<WritingDtl> writingDtlList = new ArrayList<WritingDtl>();


}
