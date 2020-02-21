package io.weeks.nuguya.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.weeks.dto.BaseTimeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "writing")
public class Writing extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long writingNo;

    private String writingDivCd;

    private String title;

    private String titleImgPath;

    private String useYn;

    @Transient
    private double averageNum;

    private int score;

    private int particiNum;

    private String regpeId;

    private String modpeId;

    @Transient
    private String solvedNum;

    @Transient
    private List<String> answer;

    @Transient
    private List<String> members;

    @OneToMany(mappedBy="writing")
    @JsonManagedReference
    private List<WritingDtl> writingDtlList = new ArrayList<WritingDtl>();

}
