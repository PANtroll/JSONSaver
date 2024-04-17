package jaworski.artur;

import jaworski.artur.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    private static IService service;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        try {
            service.perform();
        } finally {
            SpringApplication.exit(context);
        }
    }

    @Autowired
    public void setService(IService service) {
        Main.service = service;
    }
}