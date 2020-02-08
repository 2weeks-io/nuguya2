package io.weeks.nuguya.Entity;

import io.weeks.dto.BaseDto;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@ToString
public class WritingDtl extends BaseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long writingSeq;

    @ManyToOne
    @JoinColumn(name = "writing_no")
    private Writing writing;

    private String oriImgPath1;

    private String compoImgPath1;

    private String answer;

}
