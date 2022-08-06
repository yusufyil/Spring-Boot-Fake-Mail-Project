package io.smartiq.springfakemail.Service;

import io.smartiq.springfakemail.DTO.MailDTO;

import java.io.IOException;
import java.util.List;

public interface IMailService {
    MailDTO save(MailDTO mailDTO);

    List<MailDTO> findAll();

    MailDTO findOne(Long id);

    void delete(Long id);
    void clearCache();
    void sendMailToAllUsers(String mailDTO) throws IOException;
}
