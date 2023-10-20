package ihorko.work.speech_recognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpeechRecognitionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpeechRecognitionApplication.class, args);
	}
}
