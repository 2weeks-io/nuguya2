function bindObjectEvt() {

    //게시글 유형 버튼 클릭 이벤트
    $("input:radio[name=writingDivCd]").click(function () {

        var writingDivCd = $("input[name=writingDivCd]:checked").val();

        $("#dataForm")[0].reset();

        if (writingDivCd === "10") {
            $("#writingRegDivCd10").show();
            $("#writingRegDivCd20").hide();
            $("#writingRegDivCd20").find("input, select, button, textarea").prop("disabled", true);
        } else if (writingDivCd === "20") {
            $("#writingRegDivCd10").hide();
            $("#writingRegDivCd20").show();
            $("#writingRegDivCd10").find("input, select, button, textarea").prop("disabled", true);
        }

        $("input:radio[name='writingDivCd']:radio[value='"+ writingDivCd + "']").prop("checked",true);
    });

}

function addImgFile(){
    const str = `<p>
                    원본이미지: <input type="file" name="compoImgFile" style="width:200px">
                    합성이미지: <input type="file" name="oriImgFile"   style="width:200px">
                    정답: <input type="text" name="answer" style="width:200px">
                </p>`;

    var writingDivCd = $("input[name=writingDivCd]:checked").val();

    if(writingDivCd === '10'){
        $("#writingRegDivCd10").append(str);
    } else if(writingDivCd === '20'){
        $("#writingRegDivCd20").append(str);
    }

}