package io.weeks.nuguya.Entity;

import io.weeks.dto.BaseTimeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "WRITING_DTL")
public class WritingDtl extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long writingSeq;

    @ManyToOne
    @JoinColumn(name = "writing_no")
    private Writing writing;

    private String oriImgPath1;

    private String compoImgPath1;

    private String answer;

    private String regpeId;

    private String modpeId;

}
