/**
 * 
 */
package com.proximotech.zhw.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.proximotech.model.Mail;
import com.proximotech.zhw.utils.ReadEMLFile;

/**
 * @author Apple
 *
 */
@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
    ResourceLoader resourceLoader;
	
	@Autowired
	ReadEMLFile readEMLFile;

	@Override
	public Mail readBinaryFile(String string) throws Exception {
		// TODO Auto-generated method stub
		
		Resource resource = resourceLoader.getResource("classpath:"+string+".eml");
		InputStream input = resource.getInputStream();
		File emlFile = resource.getFile();
		Mail mail = readEMLFile.read(emlFile);
		
		return mail;
	}

	@Override
	public Mail writeBinaryFile(String emlFile, String answer) throws IOException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
