function bindObjectEvt() {

    //게시글 상세 삭제
    $("input:button[name=delBtn]").click(function () {
        var writingNo  = $(this).data("writingno");
        var writingSeq = $(this).data("writingseq");

        var url = "/nuguya/writingDtl/" + writingNo + "/" + writingSeq;

        param = {
            writingNo     : writingNo
            , writingSeq : writingSeq
        };

        $.ajax({
            async       : true,
            url         : url,
            traditional : true,
            type        : "DELETE",
            data        : param,
            cache       : false,
            success     : function(result){
                console.log(result.resultMsg);
                $("#"+ result.writingDtl.writingSeq).hide();
            },
            error       : function(jqXhr, status, error){
                alert("에러가 발생하였습니다.");
            }
        });

    });
}