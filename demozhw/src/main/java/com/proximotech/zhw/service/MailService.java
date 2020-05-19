/**
 * 
 */
package com.proximotech.zhw.service;

import java.io.IOException;

import com.proximotech.model.Mail;

/**
 * @author Apple
 *
 */
public interface MailService {

	Mail readBinaryFile(String emlFile) throws IOException, Exception;
	
	Mail writeBinaryFile(String emlFile, String answer) throws IOException, Exception;

}
