package controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


public class MemberDAO {
	Connection conn; 

	public MemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn
			=DriverManager.getConnection
			("jdbc:oracle:thin:@localhost:1521:xe","system","1234");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	public int joinMember(MemberVO member) {
		try {

			String sql="insert into member VALUES(?,?,?,?)";
			
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, member.getId());
			ps.setString(2, member.getPassword());
			ps.setString(3, member.getMail());
			ps.setString(4, member.getName());
			int result=ps.executeUpdate();
			ps.close();
		
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public boolean logincheck(String id, String password) {
		try {
			String sql="select * from member where id=? and password = ?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.isBeforeFirst()) {
				ps.close();
			
				return true;
			}else {
				ps.close();
			
			return false;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	public int updatemember(MemberVO member) {
		try {

			StringBuffer sql=new StringBuffer();
			sql.append("update member set password = ?,mail=?,name=? where id = ?");
			
			
			PreparedStatement ps=conn.prepareStatement(sql.toString());
			ps.setString(1, member.getPassword());
			ps.setString(2, member.getMail());
			ps.setString(3, member.getName());
			ps.setString(4, member.getId());
			int result=ps.executeUpdate();
			ps.close();
		
			return result;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int dropMember(String id) {
		try {
			String sql="delete from member where idx=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,id);
			int result=ps.executeUpdate();
			ps.close();
		
			return result;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public int sendmail(String title, String msg) {
	    MimeMessagePreparator msgg = new MimeMessagePreparator() {
	        @Override
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"utf-8");
	            message.setFrom("wldns0700@gmail.com");
	            message.setTo("wldns0700@naver.com");

	            // Use MimeUtility to encode the title
	            String encodedTitle = javax.mail.internet.MimeUtility.encodeText(title, "utf-8", "B");
	            String encodedString = javax.mail.internet.MimeUtility.encodeText(msg, "utf-8", "B");
	            message.setSubject(encodedTitle);

	            message.setText(msg);
	        }
	    };

	    ApplicationContext app = new ClassPathXmlApplicationContext("controller/mailconfig.xml");
	    JavaMailSender mail = (JavaMailSender) app.getBean("mailSender");
	    mail.send(msgg);

	    return 0;
	}
}
