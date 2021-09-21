package com.baseClasses;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Sendmail{
	public static void ComposeGmail(String fromMail,String tomail,File suitefolder)
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		Session session = Session.getDefaultInstance(props,	new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gvenkataraghavendra@gmail.com","ewfwe3");
			}
		});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromMail));
		//	message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(tomail));
			message.setRecipients(Message.RecipientType.TO,	tomail+",gvenkataraghavendra@gmail.com");
			message.setSubject("Selenium Automation Script Execution Report");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("This mail is from Selenium Automation Execution. Please find the attached execution report");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();

			//Here for Sample I am attaching TestNG Report i.e emailable-report.html ,they exist in test-output folder

			//String file= System.getProperty("user.dir")+"\\TestReports\\"+"Suite_20161017152342\\SummaryReport.pdf";
		//	String file=suitefolder+"\\SummaryReport.pdf";
		//	String file = PDFResultReport.path;
			File path = HtmlReport.filePath;
			String file= path.toString();
			System.out.println("upload file path :"+file);
			//String path2=file+"\\"+"Test"+"";
		//	String fileName = "SummaryReport.pdf";
			String fileName = file;
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			System.out.println("SENDING");
			Transport.send(message);
			System.out.println("SENT");
		} catch (MessagingException ex) {
			throw new RuntimeException(ex);
		}
	}
}