package io.smartiq.springfakemail.Controller;

import io.smartiq.springfakemail.DTO.MailDTO;
import io.smartiq.springfakemail.Service.IMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
    private final IMailService mailService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<MailDTO> listMail(){
        return mailService.findAll();
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getallmails/{id}")
    public MailDTO getMail(@PathVariable Long id){
        return mailService.findOne(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MailDTO save(@RequestBody MailDTO mailDTO){
        return mailService.save(mailDTO);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        mailService.delete(id);
    }
}
