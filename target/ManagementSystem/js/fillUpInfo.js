var checkNumber=false;
var checkAge=false;
var checkPhone=false;
var checkName=false;

$(function () {

    checkSubmit();

    $("#name").blur(function() {
        var reg=/^[\u4e00-\u9fa5\w]{1,6}$/g;
        var name=$(this).val();
        var message=$("#user-name");
        var img=$("#name-img");
        if(name==""){
            setWrongMessage(img,message,"姓名不能为空");
            checkName=false;
            return;
        }
        if(reg.test(name)){
            checkName=true;
            setCorrectMessage(img,message);
        }else{
            checkName=false;
            setWrongMessage(img,message,"姓名不规范");
            return;
        }
        $.post("../FindNameServlet",{name:name},function (data) {
            if(data.userExist==1){
                setWrongMessage(img,message,data.msg);
                checkName=false;
            }else{
                setCorrectMessage(img,message);
                checkName=true;
            }
            checkSubmit();
        },"json");
    });

    $("#number").blur(function() {
        var reg=/^[a-zA-Z0-9]{9}$/g;
        var number=$(this).val();
        var message=$("#user-number");
        var img=$("#number-img");
        if(number==""){
            setWrongMessage(img,message,"学号不能为空");
            checkNumber=false;
            return;
        }
        if(reg.test(number)){
            checkNumber=true;
            setCorrectMessage(img,message);
        }else{
            checkNumber=false;
            setWrongMessage(img,message,"学号不规范");
            return;
        }
        $.post("../FindUserNumberServlet",{number:number},function (data) {
            if(data.numberExist==1){
                setWrongMessage(img,message,data.msg);
                checkNumber=false;
            }else{
                setCorrectMessage(img,message);
                checkNumber=true;
            }
            checkSubmit();
        },"json");
    });

    $("#phone").blur(function() {
        var reg=/^[\d]{11}$/g;
        var phone=$(this).val();
        var message=$("#user-phone");
        var img=$("#phone-img");
        if(phone==""){
            setWrongMessage(img,message,"电话不能为空");
            checkPhone=false;
            return;
        }
        if(reg.test(phone)){
            checkPhone=true;
            setCorrectMessage(img,message);
        }else{
            checkPhone=false;
            setWrongMessage(img,message,"电话不规范");
            return;
        }
        $.post("../FindUserPhoneServlet",{phone:phone},function (data) {
            if(data.phoneExist==1){
                setWrongMessage(img,message,data.msg);
                checkPhone=false;
            }else{
                setCorrectMessage(img,message);
                checkPhone=true;
            }
            checkSubmit();
        },"json");
    });

    $("#age").blur(function () {
        var age=$("#age").val();
        var message=$("#user-age");
        var img=$("#age-img");
        if(age==""){
            setWrongMessage(img,message,"年龄不能为空");
            checkAge=false;
        }else{
            if(age<17||age>30){
                setWrongMessage(img,message,"年龄不规范");
                checkAge=false;
            }else{
                setCorrectMessage(img,message);
                checkAge=true;
            }
        }
        checkSubmit();
    });


    $.get("../GetAllDeptServlet",{},function (data) {

        var dept=$("#dept");

        $(data).each(function () {
            var option="<option value='"+this.deptId+"'>"+this.dept+"</option>";
            dept.append(option);
        });

    });


});

function checkSubmit() {
    if(checkNumber==true&&checkAge==true&&checkPhone==true&&checkName==true){
        $("#submit").prop("disabled",false);
    }else{
        $("#submit").prop("disabled",true);
    }
}

function noImg(obj) {
    obj.style.display="none";
}

function setWrongMessage(imgObj,messageObj,content) {
    imgObj.attr("src","../img/wrong.png");
    imgObj.css("display","inline-block");
    messageObj.css("color","red");
    messageObj.html(content);
    $("#submit").prop("disabled",true);
}

function setCorrectMessage(imgObj,messageObj) {
    imgObj.attr("src","../img/correct.png");
    imgObj.css("display","inline-block");
    messageObj.html("");
}