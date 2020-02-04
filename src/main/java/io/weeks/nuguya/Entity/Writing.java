package io.weeks.nuguya.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@ToString
public class Writing {

    @Id
    @GeneratedValue
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

    private Date   regDts;

    private Date   modDts;

}
