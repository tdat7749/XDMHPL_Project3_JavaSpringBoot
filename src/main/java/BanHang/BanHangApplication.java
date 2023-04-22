package BanHang;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("BanHang.Repository")
@EntityScan({"BanHang.Entities"})
@ComponentScan({"BanHang.Controllers","BanHang.Repository","BanHang.Services","BanHang.ViewModel"})
public class BanHangApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BanHangApplication.class, args);
	}

}

