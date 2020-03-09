package io.weeks.nuguya.Entity;

import io.weeks.dto.BaseTimeEntity;
import io.weeks.nuguya.PrimaryKey.CommCdPk;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "writing_dtl_cd")
@IdClass(CommCdPk.class)
public class WritingDtlCd extends BaseTimeEntity {

    @Id
    @Column(name = "commCd")
    private String commCd;

    @Id
    @Column(name = "commDtlCd")
    private String commDtlCd;

    private String commDtlNm;

    private String commNm;

}
