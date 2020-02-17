package io.weeks.nuguya.PrimaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class WritingDtlPk  implements Serializable {

    public WritingDtlPk(){}

    private Long writingNo;

    private Long writingSeq;

}
