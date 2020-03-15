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

//누구야 메뉴 페이지 이동
function goNuguyaMenu(){
    window.location.href = '/nuguya';
}

function updateWritingDtl() {

    var form = $('#dataForm')[0];
    var data = new FormData(form);
    var url = "/nuguya/write/detail"

    $("#submitBtn").prop("disabled", true);

    $.ajax({
        enctype     : 'multipart/form-data',
        url         : url,
        processData : false,
        contentType : false,
        type        : "POST",
        data        : data,
        cache       : false,
        timeout     : 600000,
        success     : function(result){
            alert("게시글이 업데이트 되었습니다.");
            $("#submitBtn").prop("disabled", false);
            window.location.href = '/nuguya/page/writingUpdate/' + result.writing.writingNo;
        },
        error       : function(jqXhr, status, error){
            alert("게시글 업데이트가 실패하였습니다.");
            $("#submitBtn").prop("disabled", false);
        }
    });
}

function addImgFile(){
    const str = `<p>
                    이미지: <input type="file" name="oriImgFile"   style="width:200px">
                    정답: <input type="text" name="answer" style="width:200px">
                </p>`;

    var writingDivCd = $("input[name=writingDivCd]").val();

    if(writingDivCd === '10'){
        $("#writingRegDivCd10").append(str);
    } else if(writingDivCd === '20'){
        $("#writingRegDivCd20").append(str);
    } else if(writingDivCd === '40'){
        $("#writingRegDivCd40").append(str);
    }

}