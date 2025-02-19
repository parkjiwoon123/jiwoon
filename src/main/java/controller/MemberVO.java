package controller;
public class MemberVO {
String id;
String password;
String mail;
String name;
	
	
public MemberVO() {
	// TODO Auto-generated constructor stub
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getMail() {
	return mail;
}


public void setMail(String mail) {
	this.mail = mail;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


@Override
public String toString() {
	return "MemberVO [id=" + id + ", password=" + password + ", mail=" + mail + ", name=" + name + "]";
}


public MemberVO(String id, String password, String mail, String name) {
	super();
	this.id = id;
	this.password = password;
	this.mail = mail;
	this.name = name;
}

}
