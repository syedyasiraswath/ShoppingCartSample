package com.shopping.cart.service.impl;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.shopping.cart.exception.CartException;

/**
 * @author prakhar verma
 *
 */
@Service
public class MailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired 
	private Environment configuration;
	
	@Autowired 
    private TemplateEngine templateEngine;

	private static final Logger logger = LoggerFactory.getLogger(MailService.class);

	public MailService(){}

	public static int noOfQuickServiceThreads = 20;
	
	/**
	 * this statement create a thread pool of twenty threads
	 * here we are assigning send mail task using ScheduledExecutorService.submit();
	 */
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads); // Creates a thread pool that reuses fixed number of threads(as specified by noOfThreads in this case).
	
	public void sendASynchronousMail(String toEmail,String subject,String text) throws MailException, CartException{
		logger.debug("inside sendASynchronousMail method");
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setFrom("aswathsyed@gmail.com");
		mail.setTo(toEmail);
		mail.setSubject(subject);
		mail.setText("This a ASynchronousMail : "+text);
		quickService.submit(new Runnable() {
			@Override
			public void run() {
				try{
				sender.send(mail);
				}catch(Exception e){
					logger.error("Exception occur while sending the mail : ",e);
				}
			}
		});
	}

}