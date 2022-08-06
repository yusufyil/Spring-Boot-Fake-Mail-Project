package io.smartiq.springfakemail.Configuration;

import io.smartiq.springfakemail.DTO.MailDTO;
import io.smartiq.springfakemail.Util.MailDTODeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> consumerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, MailDTODeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, MailDTO> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, MailDTO>> factory(
            ConsumerFactory<String, MailDTO> consumerFactory
    ){
        ConcurrentKafkaListenerContainerFactory<String, MailDTO> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }
}
