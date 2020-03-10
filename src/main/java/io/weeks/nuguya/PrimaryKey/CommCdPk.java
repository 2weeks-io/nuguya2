package io.weeks.nuguya.PrimaryKey;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class CommCdPk implements Serializable {

    public CommCdPk(){}

    public CommCdPk(String commCd, String commDtlCd){
        this.commCd = commCd;
        this.commDtlCd = commDtlCd;
    }

    private String commCd;

    private String commDtlCd;

}
