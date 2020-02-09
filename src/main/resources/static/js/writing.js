function bindObjectEvt() {

    //게시글 유형 버튼 클릭 이벤트
    $("input:radio[name=writingDivCd]").click(function () {

        var writingDivCd = $("input[name=writingDivCd]:checked").val();

        if (writingDivCd === "10") {
            $("#writingRegDivCd10").show();
            $("#writingRegDivCd20").hide();
        } else if (writingDivCd === "20") {
            $("#writingRegDivCd10").hide();
            $("#writingRegDivCd20").show();
        }
    });

}