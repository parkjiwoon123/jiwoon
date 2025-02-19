package consol;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class Mailsender {

	public static void main(String[] args) {
		MimeMessagePreparator msg = new MimeMessagePreparator() {
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"utf-8");
				message.setFrom("wldns0700@gmail.com");
				message.setTo("wldns0700@naver.com");
				message.setSubject("spring mail sender");
				message.setText("ㅎㅇ");
				
			}
		};
		ApplicationContext app = new ClassPathXmlApplicationContext("mail/mailconfig.xml");
		JavaMailSender mail = (JavaMailSender) app.getBean("mailSender");
		mail.send(msg);
		
}
	
}
