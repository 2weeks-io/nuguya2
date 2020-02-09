package io.weeks.nuguya.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.weeks.dto.BaseTimeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
@Table(name = "writing")
public class Writing extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long writingNo;

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
