package io.weeks.nuguya.Entity;

import io.weeks.dto.BaseTimeEntity;
import io.weeks.nuguya.PrimaryKey.WritingDtlPk;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString
@Table(name = "test_writing_score")
@IdClass(WritingDtlPk.class)
public class TestWritingScore extends BaseTimeEntity {

    @Id
    @Column(name = "writing_no")
    private Long writingNo;

    @Id
    @Column(name = "writing_seq")
    private Long writingSeq;

    @Id
    @Column(name = "optYn")
    private String optYn;

    private String target1;

    private int score1;

    private String target2;

    private int score2;

    private String target3;

    private int score3;

    private String target4;

    private int score4;

    private String target5;

    private int score5;

    private String target6;

    private int score6;

    private String target7;

    private int score7;

    private String target8;

    private int score8;

    private String target9;

    private int score9;

    private String target10;

    private int score10;

    private String regpeId;

    private String modpeId;

}
