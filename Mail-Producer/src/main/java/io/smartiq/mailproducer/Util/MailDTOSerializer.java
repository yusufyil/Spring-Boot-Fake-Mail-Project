package io.smartiq.mailproducer.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class MailDTOSerializer implements Serializer {
    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object mailDTO) {
        byte[] serializedMail = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            serializedMail = objectMapper.writeValueAsString(mailDTO).getBytes();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return serializedMail;
    }


    @Override
    public void close() {
        Serializer.super.close();
    }
}
