package br.com.bancoimpossivel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class BancoImpossivelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BancoImpossivelApplication.class, args);
    }

}