package nirmalya.aatithya.restmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import nirmalya.aatithya.restmodule.common.EnvironmentVaribles;
import nirmalya.aatithya.restmodule.util.AESUtil;
import nirmalya.aatithya.restmodule.util.DocumentUpload;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestModuleApplication { 

//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		PasswordEncoder encoder = new BCryptPasswordEncoder();
//		return encoder;
//	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	
	
	@Bean
	public EnvironmentVaribles environmentVaribles() {
		EnvironmentVaribles env = new EnvironmentVaribles();
		return env;
	}
	@Bean
	public DocumentUpload documentUpload() {
		DocumentUpload doc = new DocumentUpload();
		return doc;
	}
	
	@Bean
    public AESUtil aesUtil() {
        return new AESUtil(); // Create and return an instance of AESUtil
    }
	
	public static void main(String[] args) {
		SpringApplication.run(RestModuleApplication.class, args);
	}
}
