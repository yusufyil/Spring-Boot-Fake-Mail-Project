package io.smartiq.springfakemail.Service.Impl;

import io.smartiq.springfakemail.DTO.MailDTO;
import io.smartiq.springfakemail.Exception.Mail.MailNotFoundException;
import io.smartiq.springfakemail.Exception.User.UserNotFoundException;
import io.smartiq.springfakemail.Model.Mail;
import io.smartiq.springfakemail.Model.User;
import io.smartiq.springfakemail.Repository.MailRepository;
import io.smartiq.springfakemail.Repository.UserRepository;
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
    private final MailRepository mailRepository;
    private final UserRepository userRepository;

    @Override
    public MailDTO save(MailDTO mailDTO) {
        Mail mail = MappingHelper.map(mailDTO, Mail.class);
        Optional<User> receiver = userRepository.findById(mail.getUser().getId());
        if(receiver.isPresent()){
            Mail result = mailRepository.save(mail);
            log.info("Mail saved to the database with id {}", mail.getId());
            return MappingHelper.map(result, MailDTO.class);
        }else {
            String message = "There is no user in the database with " + mailDTO.getUserId() + " id number.";
            log.error(message);
            throw new UserNotFoundException(message);
        }
    }

    @Override
    public List<MailDTO> findAll() {
        List<Mail> mailList = mailRepository.findAll();
        log.info("all mails have been pulled from database.");
        return MappingHelper.mapList(mailList, MailDTO.class);
    }

    @Override
    public MailDTO findOne(Long id) {
        Optional<Mail> mail = mailRepository.findById(id);
        if(mail.isPresent() && mail.get().isActive()){
            log.info("mail with {} has been pulled from database", id);
            return MappingHelper.map(mail.get(), MailDTO.class);
        } else{
            String message = "Mail not found by given " + id + " id number.";
            log.error(message);
            throw new MailNotFoundException(message);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Mail> mail = mailRepository.findById(id);
        if(mail.isPresent() && mail.get().isActive()){
            mail.get().setActive(false);
            log.info("Mail with id number {} has been soft deleted", id);
        }else {
            String message = "There is no mail şn the database with " + id + " id number";
            log.error(message);
            throw new MailNotFoundException(message);
        }
    }
}
