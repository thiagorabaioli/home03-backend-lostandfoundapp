package tfr.LostAndFoundAPP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableAsync
public class LostAndFoundAppApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LostAndFoundAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("123456"));
		boolean result = passwordEncoder.matches("123456","$2a$10$T0yoFdiWiA1HH/Oysq/ucOPr0hdu0dvlKk.hm8TH52WNn3l86wE6C");

		System.out.println(result);
	}
}
