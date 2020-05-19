/**
 * 
 */
package com.proximotech.zhw;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Apple
 *
 */
@Configuration
public class EMLConfiguration {

	@Bean
	public Properties getSMTPProperties() {
		Properties mailProperties = System.getProperties();
		mailProperties.put("mail.host", "smtp.dummydomain.com");
		mailProperties.put("mail.transport.protocol", "smtp");
		return mailProperties;
	}

}
