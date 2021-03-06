function bindObjectEvt() {

    //게시글 유형 버튼 클릭 이벤트
    $("input:radio[name=writingDivCd]").click(function () {

        var writingDivCd = $("input[name=writingDivCd]:checked").val();

        $("#dataForm")[0].reset();

        //등록구분에따른, show hide 처리 및 disabled 처리
        if (writingDivCd === "10") {
            $("div[name=writingRegDivCd]").hide();
            $("div[name=writingRegDivCd]").find("input, select, button, textarea").prop("disabled", true);
            
            $("#writingRegDivCd10").show();
            $("#writingRegDivCd10").find("input, select, button, textarea").prop("disabled", false);

            $("#writingDtlTypeBox").hide();
        } else if (writingDivCd === "20") {
            $("div[name=writingRegDivCd]").hide();
            $("div[name=writingRegDivCd]").find("input, select, button, textarea").prop("disabled", true);

            $("#writingRegDivCd20").show();
            $("#writingRegDivCd20").find("input, select, button, textarea").prop("disabled", false);

            $("#writingDtlTypeBox").hide();
        } else if(writingDivCd === "30"){
            $("div[name=writingRegDivCd]").hide();
            $("div[name=writingRegDivCd]").find("input, select, button, textarea").prop("disabled", true);

            $("#writingRegDivCd30").show();
            $("#writingRegDivCd30").find("input, select, button, textarea").prop("disabled", false);

            $("#writingDtlTypeBox").hide();
        } else if(writingDivCd === "40"){
            $("div[name=writingRegDivCd]").hide();
            $("div[name=writingRegDivCd]").find("input, select, button, textarea").prop("disabled", true);

            $("#writingRegDivCd40").show();
            $("#writingRegDivCd40").find("input, select, button, textarea").prop("disabled", false);

            $("#writingDtlTypeBox").show();
        }

        $("input:radio[name='writingDivCd']:radio[value='"+ writingDivCd + "']").prop("checked",true);
    });

    //게시글 유형 상세 클릭 시
    $("input:radio[name=writingDtlCd]").click(function () {
        var writingDivCd = $("input[name=writingDivCd]:checked").val();
        var writingDtlCd = $("input[name=writingDtlCd]:checked").val();

        if(writingDivCd == '40'){

            if(writingDtlCd == '10'){
                $("div[name=dirEnrollBox]").hide();
                $("div[name=crawEnrollBox]").show();
            } else if(writingDtlCd == '20'){
                $("div[name=dirEnrollBox]").show();
                $("div[name=crawEnrollBox]").hide();
            }

        } else{

        }

    });

    <!-- 테스트 유형 게임(만들다가 일단 중단) -->
    $("input:button[name=addTestTypeBtn]").click(function () {

        var targets = $("#target").val();

        //공백 제거
        targets.replace(/(\s*)/g, "") ;

        var targetList = targets.split(',');

        var tblHeader = "<th>질문" + "</th>" +"<th>Yes or No" + "</th>"
        $("#writingScoreTbl").append(tblHeader);

        for ( var i in targetList ) {
            var tableHeader ="<th>" + targetList[i] +"</th>";
            $("#writingScoreTbl").append(tableHeader);
        }


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