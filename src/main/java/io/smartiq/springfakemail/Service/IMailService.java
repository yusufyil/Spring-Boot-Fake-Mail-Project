package io.smartiq.springfakemail.Service;

import io.smartiq.springfakemail.DTO.MailDTO;

import java.util.List;

public interface IMailService {
    MailDTO save(MailDTO mailDTO);
    List<MailDTO> findAll();
    MailDTO findOne(Long id);
    void delete(Long id);
}
