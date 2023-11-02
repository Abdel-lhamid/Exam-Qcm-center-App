package ma.ensaf.Qcmexamcenterbackend;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class QcmExamCenterBackEndApplication implements ApplicationContextAware {
	private static ApplicationContext CONTEXT;


	public static void main(String[] args) {
		SpringApplication.run(QcmExamCenterBackEndApplication.class, args);
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		CONTEXT = applicationContext;
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
