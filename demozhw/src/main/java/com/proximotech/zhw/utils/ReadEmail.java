package com.proximotech.zhw.utils;
import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;

public class ReadEmail {

   public static void main(String args[]) throws Exception{
       display(new File("E:\\PROXIMOTECH\\resources\\test_sample_message.eml"));
//       display(new File("E:\\PROXIMOTECH\\resources\\sample.eml"));

   }

   public static void display(File emlFile) throws Exception{
        Properties props = System.getProperties();
        props.put("mail.host", "smtp.dummydomain.com");
        props.put("mail.transport.protocol", "smtp");

        Session mailSession = Session.getDefaultInstance(props, null);
        InputStream source = new FileInputStream(emlFile);
        MimeMessage message = new MimeMessage(mailSession, source);


        System.out.println("Subject : " + message.getSubject());
        System.out.println("From : " + message.getFrom()[0]);
        System.out.println("--------------");
        System.out.println("Body : " +  message.getContent());
        
        MimeMultipart  mimeMultipart = (MimeMultipart) message.getContent();
        System.out.println(getTextFromMimeMultipart(mimeMultipart));
        
        System.out.println(message.getDisposition());
        
        
    }
   
   /**
    * Extracts the text content of a multipart email message
    */
   private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
       String result = "";
       int partCount = mimeMultipart.getCount();
       
       System.out.println("Part count : "+partCount);
       
       for (int i = 0; i < partCount; i++) {
           BodyPart bodyPart = mimeMultipart.getBodyPart(i);
           
           String disp = bodyPart.getDisposition();
           
           if(Part.ATTACHMENT.equalsIgnoreCase(disp)) {
        	   System.out.println("File Name : "+bodyPart.getFileName());
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
}