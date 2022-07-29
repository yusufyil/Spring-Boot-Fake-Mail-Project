package io.smartiq.springfakemail.Controller;

import io.smartiq.springfakemail.DTO.MailDTO;
import io.smartiq.springfakemail.Service.IMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final IMailService mailService;

    @GetMapping(value = "/getallmails")
    public ResponseEntity<List<MailDTO>> listMail() {
        return new ResponseEntity<>(mailService.findAll(), OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MailDTO> getMail(@PathVariable Long id) {
        return new ResponseEntity<>(mailService.findOne(id), OK);
    }

    @PostMapping
    public ResponseEntity<MailDTO> save(@RequestBody MailDTO mailDTO) {
        return new ResponseEntity<>(mailService.save(mailDTO), CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        mailService.delete(id);
        return new ResponseEntity(OK);
    }
}
