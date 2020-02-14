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

    @OneToMany(mappedBy="writing")
    @JsonManagedReference
    private List<WritingDtl> writingDtlList = new ArrayList<WritingDtl>();

    private String writingDivCd;

    private String writingExplain;

    private String title;

    private String titleImgPath;

    private String banImgPath1;

    private String banImgPath2;

    private String banImgPath3;

    private String banImgPath4;

    private String banImgPath5;

    private String useYn;

    private int score;

    private int particiNum;

    private String regpeId;

    private String modpeId;

}
