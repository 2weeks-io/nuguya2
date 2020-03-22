package io.weeks.nuguya.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.weeks.dto.BaseTimeEntity;
import io.weeks.nuguya.PrimaryKey.WritingDtlPk;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@ToString(exclude = "writing")
@Table(name = "writing_dtl")
@IdClass(WritingDtlPk.class)
public class WritingDtl extends BaseTimeEntity{

    @ManyToOne
    @JoinColumn(name = "writing_no", insertable = false, updatable = false)
    @JsonBackReference
    private Writing writing;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "writing_seq", updatable=false,nullable=false)
    private Long writingSeq;

    @Id
    @Column(name = "writing_no")
    private Long writingNo;

    private String oriImgPath1;

    private String compoImgPath1;

    private String answer;

    private String example1;

    private String example2;

    private String regpeId;

    private String modpeId;

    private String question;

    @Transient
    private List<String> randAnswer;

}
