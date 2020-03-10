package io.weeks.nuguya.Entity;

import io.weeks.dto.BaseTimeEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "writingCd")
public class WritingCd extends BaseTimeEntity {

    @Id
    @Column(name = "commCd")
    private String commCd;

    private String commCdNm;

    private String commCdDesc;

    private String regpeId;

    private String modpeId;

}
