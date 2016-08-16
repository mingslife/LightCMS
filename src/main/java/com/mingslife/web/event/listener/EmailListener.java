package com.mingslife.web.event.listener;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeUtility;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.mingslife.web.event.EmailEvent;

public class EmailListener implements ApplicationListener {
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof EmailEvent) {
			handle((EmailEvent) event);
		}
	}

	private void handle(EmailEvent event) {
		System.out.println("正在发送邮件...");
		System.out.println("地址: " + event.getAddress());
		System.out.println("标题: " + event.getSubject());
		System.out.println("内容:\n" + event.getContent());
		/*try {
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		send(event.getAddress(), event.getSubject(), event.getContent());
		System.out.println("邮件发送完成");
	}
	
	private void send(final String address, final String subject, final String content) {
		// 初始化props
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.mxhichina.com");
		// 创建session
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 验证
				return new PasswordAuthentication("test@mingslife.com", "Test2016");
			}
		});
		final MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(MimeUtility.encodeText("测试账号") + " <test@mingslife.com>"));
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
			message.setSubject(subject);
			message.setContent(content, "text/html; charset=UTF-8");
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
