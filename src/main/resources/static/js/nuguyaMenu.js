function bindObjectEvt() {

    //게시글 작성 페이지 이동
    $("input[name='pageWritingBtn']").click(function(){
        window.location.href = '/nuguya/page/writing';
    });

    //모자이크 게임 게시글 리스트 페이지 이동
    $("input[name='mozaWritingBtn']").click(function(){
        window.location.href = '/nuguya/page/writingCategory/10';
    });
}