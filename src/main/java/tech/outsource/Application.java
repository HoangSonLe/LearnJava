package tech.outsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//GlobalExceptionHandler không chạy vì không nằm trong phạm vi quét của Spring.
//Giải pháp: đưa Application lên package tech hoặc thêm scanBasePackages để quét tech.core.
@SpringBootApplication(scanBasePackages = {"tech.outsource", "tech.core"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
