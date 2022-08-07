package io.smartiq.mailproducer;

import io.smartiq.mailproducer.Model.MailDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.RecursiveTask;

@SpringBootApplication
public class MailProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailProducerApplication.class, args);
    }

}
