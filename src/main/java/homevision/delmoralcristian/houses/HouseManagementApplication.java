package homevision.delmoralcristian.houses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class HouseManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(HouseManagementApplication.class, args);

    }
}

