 /*로그인처리*/ 	
    function logincheck(){
   	
           var idobj=document.querySelector("#id");
           var passobj=document.querySelector("#password");
   		
           
           if(idobj.value==""){
               alert("아이디란을 입력하세요.");
               idobj.focus();
               return;
           }
         
           else if(idobj.value.length<4){
               alert('아이디 길이를 확인하세요.')
               idobj.value="";
               idobj.focus();
               return;
           }
           
           else if(passobj.value==""){
               alert("패스워드를 입력하세요.");
               passobj.focus();
               return;
           }else if(passobj.value.length<4){  
               alert('패스워드를 확인하세요.')
               passobj.value="";
               passobj.focus();
               return;
           }//정규식조건 추가
           
           //폼에 있는 내용을 다른 파일로 전송
           document.forms[0].requestSubmit();
           
       }


        function agr(){
        	if(document.querySelector("#cbox").checked){
        		mainPageChange("/member/member_valueconfirm.html");
        	}else{
        		alert("약관에 동의 하셔야 다음으로 진행됩니다.")	
        	}
        }
        /*회원가입확인*/
     	function confirm2(){
    	//alert('test')
    	//1.빈란을 확인하라(id속성 활용)
    	//2.길이를 확인하라(value.length)
    	//3.문자형식(대문자, 소문자, 숫자, 특수문자, 길이를 확인하라/정규표현식)
    	if(document.querySelector("#id").value==""){
    		alert('아이디가 비어 있습니다.')
    		document.querySelector("#id").focus();
    		return ; //return이라는 키워드를 만나는 순간 함수는 종료된다.
    	}
    	if(document.querySelector("#password").value==""){
    		alert('패스워드가 비어 있습니다.')
    		document.querySelector("#password").focus();
    		return ; //return이라는 키워드를 만나는 순간 함수는 종료된다.
    	}
    	if(document.querySelector("#hp").value==""){
    		alert('핸드폰번호가 비어 있습니다.')
    		document.querySelector("#hp").focus();
    		return ; //return이라는 키워드를 만나는 순간 함수는 종료된다.
    	}
    	if(document.querySelector("#address").value==""){
    		alert('주소가 비어 있습니다.')
    		document.querySelector("#address").focus();
    		return ; //return이라는 키워드를 만나는 순간 함수는 종료된다.
    	}
    	//비밀번호가 인증되었는지 여부 확인
    	if(!pass){
    		alert('패스워드를 인증하세요.');
    		return;
    	}
    	//아이디 길이를 확인/패스워드길이 확인
    	//8~12자리로 제한을 할경우
    	id=document.querySelector("#id").value;
    	if(id.length<4 || id.length>12 ){
    		alert('아이디 길이를 확인하세요.');
    		document.querySelector("#id").focus();
    		return;
    	}
    	/* document.forms[0].requestSubmit(); */ //form내에 submit버튼이 없을 경우 실행하는 함수
    	document.forms[0].submit(); //from내에 submit이 있는 경우에 실행하는 함수
    	
    }

    var pass=false;
    function passconfirm(){
    	p1=document.querySelector("#password").value;
    	p2=document.querySelector("#repassword").value;
    	if(p1==p2){
    		alert('비밀번호가 인증되었습니다.');
    		document.querySelector("#pconfirm").value="인증완료";
    		document.querySelector("#pconfirm").disabled='disabled';//버튼비활성화
    		pass=true;
    	}else{
    		alert('비밀번호를 다시 확인하세요.')
    	}
    }