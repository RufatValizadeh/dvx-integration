package az.dvx.dvx;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@SpringBootApplication
public class DvxApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DvxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		KeyGenerator keyGen = null;
//		try {
//			keyGen = KeyGenerator.getInstance("AES");
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException(e);
//		}
//		keyGen.init(128);
//		SecretKey secretKey = keyGen.generateKey();
//		byte[] raw = secretKey.getEncoded();
//		System.out.println(Base64.getEncoder().encodeToString(raw));
	}
}
