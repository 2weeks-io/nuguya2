
function bindObjectEvt(){
    
    //게시글 유형 버튼 클릭 이벤트
    $("input:radio[name=writingRegDivCd]").click(function(){

        var writingRegDivCd = $("input[name=writingRegDivCd]:checked").val();

        if(writingRegDivCd === "10"){
            $("#writingRegDivCd10").show();
            $("#writingRegDivCd20").hide();
        }else if(writingRegDivCd === "20"){
            $("#writingRegDivCd10").hide();
            $("#writingRegDivCd20").show();
        }
    });

}