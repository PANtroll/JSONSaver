package jaworski.artur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    private static Service service;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        service.perform();
        SpringApplication.exit(context);
    }

    @Autowired
    public void setService(Service service) {
        Main.service = service;
    }
}