package io.weeks.nuguya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class NuguyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NuguyaApplication.class, args);
    }

}
