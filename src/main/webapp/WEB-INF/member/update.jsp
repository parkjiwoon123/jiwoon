<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

function validation() {
	var id = document.getElemenById("id");
	var password = document.getElemenById("password");
	var mail = document.getElemenById("mail");
	var name = document.getElemenById("name");
	
	if(id.value==""){
	alert('아이디가 비어있습니다')
	id.focus();
	return false;
	
	}
	if(id.value==""){
		alert('이메일이 비어있습니다')
		mail.focus();
		return false;
	}
	if(id.value==""){
		alert('비밀번호가 비어있습니다')
		password.focus();
		return false;
	}
	if(id.value==""){
		alert('이름이 비어있습니다')
		name.focus();
		return false;
	}
	if(!(/[a-z0-9]+@[a-z]+\.[a-z]{2,3}/.test(mail.value))){}
	alert('이메일 형식이 아닙니다');
	mail.foucs();
	return false;
};

</script>


<main>


<form id="userinfoForm" action="updateproc" method="post" onsubmit="return Validation();">
    <table border="1" width="800" height="400" cellspacing="0">
        <tr>
            <td bgcolor="#48dbfb" align="center" colspan="2" height="50"> <b>회원 기본 정보</b> </td>
        </tr>
        <tr>
            <td bgcolor="#c8d6e5" align="center">아이디:</td>
            <td bgcolor="white"  align="left">&nbsp;<input type="text" size="20" name="id" id="id" minlength="4" maxlength="12" value="${id}" readonly="readonly"> 4~12자의 영문 대소문자의 숫자로만 입력 </td>
        </tr>
        <tr>
            <td bgcolor="#c8d6e5" align="center">비밀번호:</td>
            <td bgcolor="white"   align="left">&nbsp;<input type="password" size="20" name="password" id="password" minlength="4" maxlength="12"> 4~12자의 영문 대소문자의 숫자로만 입력</td>
        </tr>
        <tr>
            <td bgcolor="#c8d6e5" align="center">비밀번호확인:</td>
            <td bgcolor="white"  align="left">&nbsp;<input type="password" size="20" name="chkpassword" id="chkpassword"></td>
        </tr>
        <tr>
            <td bgcolor="#c8d6e5" align="center">메일주소:</td>
            <td bgcolor="white"  align="left">&nbsp;<input type="email" size="30" name="mail" id="mail"> 예) id@domain.com</td>
        </tr>
        <tr>
            <td bgcolor="#c8d6e5" align="center">이름:</td>
            <td bgcolor="white"  align="left">&nbsp;<input type="text" size="30" name="name" id="name"></td>
        </tr>
    </table>
    <br>
    <div align="center">
        <input type="submit" width="30" id="btn" value="전송">
        <input type="reset" onclick="alert('초기화 했습니다.')" value="다시 입력">
    </div>
    </form>

</main>