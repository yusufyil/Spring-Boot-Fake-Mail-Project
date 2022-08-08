package io.smartiq.springfakemail.service;

import io.smartiq.springfakemail.dto.MailDTO;

import java.io.IOException;
import java.util.List;

public interface IMailService {
    MailDTO save(MailDTO mailDTO);

    List<MailDTO> findAll();

    MailDTO findOne(Long id);

    List<MailDTO> findAllMailsOfOneUser(String username);

    void delete(Long id);

    void clearCache();

    void sendMailToAllUsers(String mailDTO) throws IOException;
}
