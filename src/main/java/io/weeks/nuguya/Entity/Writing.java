package io.weeks.nuguya.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
public class Writing {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long writingNo;

    /*
    @OneToMany
    @JoinColumn(name = "writing_no")
    private List<WritingDtl> writingDtls;
     */

    /*
    @OneToMany
    @JoinColumn(name = "writing_no")
    private List<WritingDtl> writingDtls = new ArrayList<>();
    */

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
