package io.weeks.nuguya.Entity;

import io.weeks.dto.BaseTimeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "writing_type")
public class WritingType extends BaseTimeEntity {

    @Id
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

    private String regpeId;

    private String modpeId;

    @Transient
    private int particiNum;

}
