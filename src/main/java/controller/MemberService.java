package controller;

import org.springframework.stereotype.Service;

@Service
public class MemberService {
	MemberDAO dao=new MemberDAO();
	
	public MemberService() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean loginchek(String id,String password) {
		return dao.logincheck(id, password);
	}
	public int joinMember(MemberVO member) {
		return dao.joinMember(member);
		
	}
	public int updateMember(MemberVO member) {
		return dao.updatemember(member);
		
	}
		
	
	public int del(String id) {
		return dao.dropMember(id);
		
	}

	public int mailsend(String title, String msg) {
		
		return dao.sendmail(title,msg);
		
	}
}
