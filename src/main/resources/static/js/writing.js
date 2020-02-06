
function bindObjectEvt(){
    
    //게시글 유형 버튼 클릭 이벤트
    $("input:radio[name=writingRegDivCd]").click(function(){

        if($("input[name=writingRegDivCd]:checked").val() == "10"){
            $("#writingRegDivCd10").show();
            $("#writingRegDivCd20").hide();
        }else if($("input[name=writingRegDivCd]:checked").val() == "20"){
            $("#writingRegDivCd10").hide();
            $("#writingRegDivCd20").show();
        }
    });

}