package io.smartiq.springfakemail.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smartiq.springfakemail.Model.Mail;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class MailDeserializer implements Deserializer {
    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Mail mail = null;
        try {
            mail = mapper.readValue(bytes, Mail.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return mail;
    }

    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
