package io.weeks.nuguya.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@ToString
public class WritingDtl {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long writingSeq;

    private String oriImgPath1;

    private String compoImgPath1;

    private String answer;

    private String regpeId;

    private String modpeId;

    private Date modDts;

    private Date regDts;

}
