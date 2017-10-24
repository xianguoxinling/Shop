/**
 * Created by Tooko on 2016/4/26.
 */
$(function() {
    setTimeout("changeDivStyle();", 100); // 0.1ÃëÊ±ÑÓ
});
function changeDivStyle(){
    var o_status = $("#o_status").val();
  /*  var o_status = 3;*/
    if(o_status==0){
        $('#radiu1').css('background', '#458B74');
        $('#radiu2').css('background', '#DD0000');
        $('#step1').css('color', '#458B74');
        $('#step2').css('color', '#DD0000');
    }else if(o_status==1){
        $('#radiu1').css('background', '#458B74');
        $('#radiu2').css('background', '#458B74');
        $('#radiu3').css('background', '#DD0000');
        $('#step1').css('color', '#458B74');
        $('#step2').css('color', '#458B74');
        $('#step3').css('color', '#DD0000');
    }else if(o_status==2){
        $('#radiu1').css('background', '#458B74');
        $('#radiu2').css('background', '#458B74');
        $('#radiu3').css('background', '#458B74');
        $('#radiu4').css('background', '#DD0000');
        $('#step1').css('color', '#458B74');
        $('#step2').css('color', '#458B74');
        $('#step3').css('color', '#458B74');
        $('#step4').css('color', '#DD0000');
    }else if(o_status>=3){
        $('#radiu1').css('background', '#458B74');
        $('#radiu2').css('background', '#458B74');
        $('#radiu3').css('background', '#458B74');
        $('#radiu4').css('background', '#458B74');
        $('#radiu5').css('background', '#458B74');
        $('#step1').css('color', '#458B74');
        $('#step2').css('color', '#458B74');
        $('#step3').css('color', '#458B74');
        $('#step4').css('color', '#458B74');
        $('#step5').css('color', '#458B74');
    }
}