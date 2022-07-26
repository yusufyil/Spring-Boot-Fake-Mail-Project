package io.smartiq.springfakemail.Service.Impl;

import io.smartiq.springfakemail.DTO.MailDTO;
import io.smartiq.springfakemail.Model.Mail;
import io.smartiq.springfakemail.Repository.IMail;
import io.smartiq.springfakemail.Service.IMailService;
import io.smartiq.springfakemail.Util.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class MailServiceImpl implements IMailService {
    private final IMail iMail;

    @Override
    public MailDTO save(MailDTO mailDTO) {
        Mail mail = MappingHelper.map(mailDTO, Mail.class);
        Mail result = iMail.save(mail);
        log.info("Mail saved to the database with {}", mail.getId());
        return MappingHelper.map(result, MailDTO.class);
    }

    @Override
    public List<MailDTO> findAll() {
        List<Mail> mailList = iMail.findAll();
        log.info("all mails have been pulled from database.");
        return MappingHelper.mapList(mailList, MailDTO.class);
    }

    @Override
    public MailDTO findOne(Long id) {
        Optional<Mail> mail = iMail.findById(id);
        log.info("mail with {} has been pulled from database", id);
        return MappingHelper.map(mail.get(), MailDTO.class);
    }

    @Override
    public void delete(Long id) {
        Mail mail = iMail.getById(id);
        iMail.delete(mail);
        log.warn("mail with {} id has been deleted permanently!", id);
    }
}
