package io.smartiq.mailproducer.Controller;


import io.smartiq.mailproducer.Model.MailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailProducerController {

    private final KafkaTemplate<String, MailDTO> kafkaTemplate;

    @PostMapping(value = "/toallusers")
    public ResponseEntity save(@RequestBody MailDTO mailDTO) {
        kafkaTemplate.send("myTopic", mailDTO);
        return new ResponseEntity(OK);
    }
}
