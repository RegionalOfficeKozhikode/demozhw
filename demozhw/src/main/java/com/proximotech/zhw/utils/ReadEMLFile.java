/**
 * 
 */
package com.proximotech.zhw.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.proximotech.model.Mail;
import com.proximotech.zhw.EMLConfiguration;

/**
 * @author Apple
 *
 */
@Component
public class ReadEMLFile {

	@Autowired
	EMLConfiguration emlConfiguration;

	public Mail read(File emlFile) throws Exception {

		Mail mail = new Mail();

		Session mailSession = Session.getDefaultInstance(getSMTPProperties(), null);
		InputStream source = new FileInputStream(emlFile);
		MimeMessage message = new MimeMessage(mailSession, source);

		mail.setFrom(message.getFrom()[0].toString());
		mail.setTo(message.getReplyTo()[0].toString());
		mail.setSubject(message.getSubject());

		System.out.println("Body : " + message.getContent());

		MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
		System.out.println(getTextFromMimeMultipart(mimeMultipart));

		return mail;
	}

	private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
		String result = "";
		int partCount = mimeMultipart.getCount();

		System.out.println("Part count : " + partCount);

		for (int i = 0; i < partCount; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);

			String disp = bodyPart.getDisposition();

			if (Part.ATTACHMENT.equalsIgnoreCase(disp)) {
				System.out.println("File Name : " + bodyPart.getFileName());
			}

			if (bodyPart.isMimeType("text/plain")) {
				result = result + "\n" + bodyPart.getContent();
				break; // without break same text appears twice in my tests
			} else if (bodyPart.isMimeType("text/html")) {
				String html = (String) bodyPart.getContent();
				// result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
				result = html;
			} else if (bodyPart.getContent() instanceof MimeMultipart) {
				result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
			}
		}
		return result;
	}

	private Properties getSMTPProperties() {
		return emlConfiguration.getSMTPProperties();
	}

}
