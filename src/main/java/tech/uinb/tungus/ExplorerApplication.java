package tech.uinb.tungus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExplorerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExplorerApplication.class, args);
    }

}
