package io.dowlathbasha.logsmonitoringelk.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LogsMonitoringController {
	private static final Logger LOGGER=LoggerFactory.getLogger(LogsMonitoringController.class.getName());

	@Autowired
	RestTemplate restTemplete;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/logsmonitoring")
	public String helloWorld() {
		String response = "Welcome to Extreme Networks.....!!!!" + new Date();
		LOGGER.info(Level.INFO + ",  "+ response);
		return response;
	}

	@RequestMapping(value = "/exception")
	public String exception() {
		String response = "";
		try {
			throw new Exception("Exception has occured....");
		} catch (Exception e) {
			LOGGER.error("Error....: ", e);
			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String stackTrace = sw.toString();
			LOGGER.error("Exception - " + stackTrace);
			response = stackTrace;
		}

		return response;
	}
}
