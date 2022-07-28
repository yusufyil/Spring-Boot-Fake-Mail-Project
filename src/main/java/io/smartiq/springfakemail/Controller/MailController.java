package io.smartiq.springfakemail.Controller;

import io.smartiq.springfakemail.DTO.MailDTO;
import io.smartiq.springfakemail.Service.IMailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final IMailService mailService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getallmails")
    public List<MailDTO> listMail(){
        return mailService.findAll();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public MailDTO getMail(@PathVariable Long id){
        return mailService.findOne(id);//mail not found ex.
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MailDTO save(@RequestBody MailDTO mailDTO){
        return mailService.save(mailDTO);//user not found ex.
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        mailService.delete(id);//entity not found
    }
}
