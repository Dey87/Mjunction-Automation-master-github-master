package com.baseClasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.mail.search.SubjectTerm;

import com.components.eTenderComponent;

public class EmailUtils {
	private Store store;
	private Folder folder;

	public EmailUtils() {

		try {
			Properties props = new Properties();
		
			props.put("mail.store.protocol", "imaps");
			props.put("mail.imap.ssl.enable", "true");
			props.put("mail.mime.base64.ignoreerrors", "true");
			
			Session session = Session.getInstance(props);
			store = session.getStore("imaps");
		
			try {
				store.connect("imap.gmail.com", eTenderComponent.getDataFromPropertiesFile("EmailId"),
						eTenderComponent.getDataFromPropertiesFile("Password"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				folder = store.getFolder(eTenderComponent.getDataFromPropertiesFile("FolderName"));
			} catch (IOException e) {
				e.printStackTrace();
			}

			folder.open(Folder.READ_ONLY);

		} catch (NoSuchProviderException e) {

			e.printStackTrace();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
	}

	public int getNumberOfMessages() {
		try {
			return folder.getMessageCount();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		return 0;
	}

	public int getNumberOfUnreadMessages() {
		try {
			return folder.getUnreadMessageCount();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		return 0;

	}

	public Message getMessageByIndex(int index) {
		try {
			return folder.getMessage(index);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;

	}

	public Message getLatestMessage() {
		try {
			return getMessageByIndex(getNumberOfMessages());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSpecificMailsubject(Message message) {
		try {
			return message.getSubject();
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		return null;
	}

	public String getSpecificMailBodyContent(Message mail,String bodyContent,String bodyContentlast) {
		try {
			return getEmailBody(mail).substring(getEmailBody(mail).indexOf(bodyContent),getEmailBody(mail).indexOf(bodyContentlast)).replaceAll("<br>","");
		} catch (IOException | MessagingException e) {

			e.printStackTrace();
		}
		return null;

	}

	public static String getEmailBody(Message email) throws IOException, MessagingException {

		String line, emailContentEncoded;
		StringBuffer bufferEmailContentEncoded = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));
		while ((line = reader.readLine()) != null) {
			bufferEmailContentEncoded.append(line);
		}

		emailContentEncoded = bufferEmailContentEncoded.toString();

		if (email.getContentType().toLowerCase().contains("multipart/related")) {

			emailContentEncoded = emailContentEncoded.substring(emailContentEncoded.indexOf("base64") + 6);
			emailContentEncoded = emailContentEncoded.substring(0, emailContentEncoded.indexOf("Content-Type") - 1);

			String emailContentDecoded = new String(
					new org.apache.commons.codec.binary.Base64().decode(emailContentEncoded.toString().getBytes()));
			return emailContentDecoded;
		}

		return emailContentEncoded;

	}
	
	private Map<String, Integer> getStartAndEndIndices(int max) throws MessagingException {
		
	    int endIndex = getNumberOfMessages();
	    int startIndex = endIndex - max;

	    if(startIndex < 1){
	      startIndex = 1;
	    }
	    HashMap<String,Integer> indices = new HashMap<String,Integer>();
	    
	    indices.put("startIndex", startIndex);
	    indices.put("endIndex", endIndex);

	    return indices;
	  }
	
	
	public Message[] getMessages(int maxToGet) throws MessagingException {
	    Map<String, Integer> indices = getStartAndEndIndices(maxToGet);
	    return folder.getMessages(indices.get("startIndex"), indices.get("endIndex"));
	  }	
	
	
	public Message[] getMessagesBySubject(String subject, int maxToSearch) throws Exception{
	    Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);
	   
	    Message messages[] = folder.search(
	        new SubjectTerm(subject),
	        folder.getMessages(indices.get("startIndex"), indices.get("endIndex")));
	 
	    return messages;
	  }
}
