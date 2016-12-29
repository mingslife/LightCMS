package com.mingslife.web.event.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.mingslife.web.event.EmailEvent;

public class EmailListener implements ApplicationListener {
	@Autowired
	ServletContext application;

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
		Map<String, String> applicationMap = (HashMap<String, String>) application.getAttribute("application");
		final String mailServer = (String) applicationMap.get("mailServer");
		final String mailAddress = (String) applicationMap.get("mailAddress");
		final String mailPassword = (String) applicationMap.get("mailPassword");
		final String mailNickname = (String) applicationMap.get("mailNickname");
		
		// 初始化props
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", mailServer);
		// 创建session
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 验证
				return new PasswordAuthentication(mailAddress, mailPassword);
			}
		});
		final MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(MimeUtility.encodeText(mailNickname) + " <" + mailAddress + ">"));
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
			message.setSubject(subject);
			message.setContent(content, "text/html; charset=UTF-8");
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
