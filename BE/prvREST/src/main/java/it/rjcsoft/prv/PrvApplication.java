package it.rjcsoft.prv;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import it.rjcsoft.prv.config.PrvRestConfig;

@SpringBootApplication
@PropertySource("file:./conf/application.properties")
public class PrvApplication implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private static final String PRV_TID = "PRV_TID";
	
	@Autowired
	private PrvRestConfig prvRestConfig;

	public static void main(String[] args) {
		System.getProperties().setProperty("oracle.jdbc.J2EE13Compliant", "true");
		SpringApplication.run(PrvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MDC.put(PRV_TID, "MAIN");
		log.info("Application started args={}", StringUtils.join(args));
		// String subject = "prvREST application started";
		// ResponseEnum resp = ResponseEnum.SERVER_ERROR;
		// try {
		// 	UserDTO userDTO = userService.getUsers();
		// 	resp = userDTO.getResponseEnum();
		// } finally {
		// 	String status = String.format("Application status: %s", resp);
		// 	log.debug("{}", status);
		// 	if (this.prvRestConfig.getSendStartupMail()) 
		// 		userService.sendMailToDev(subject, status);
		// }
	}

}
