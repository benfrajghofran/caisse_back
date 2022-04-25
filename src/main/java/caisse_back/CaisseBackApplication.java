package caisse_back;

import caisse_back.services.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class CaisseBackApplication {
	public static void main(String[] args) {

		new File(ProductServiceImpl.uploadDirectory).mkdir();
		SpringApplication.run(CaisseBackApplication.class, args);
	}

}
