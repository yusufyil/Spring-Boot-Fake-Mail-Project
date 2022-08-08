package io.smartiq.springfakemail.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.smartiq.springfakemail.dto.MailDTO;
import io.smartiq.springfakemail.exception.mail.MailNotFoundException;
import io.smartiq.springfakemail.exception.user.UserNotFoundException;
import io.smartiq.springfakemail.model.Mail;
import io.smartiq.springfakemail.model.User;
import io.smartiq.springfakemail.repository.MailRepository;
import io.smartiq.springfakemail.repository.UserRepository;
import io.smartiq.springfakemail.service.IMailService;
import io.smartiq.springfakemail.util.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
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
        if (receiver.isPresent()) {
            Mail result = mailRepository.save(mail);
            log.info("Mail saved to the database with id {}", mail.getId());
            return MappingHelper.map(result, MailDTO.class);
        } else {
            String message = "There is no user in the database with " + mailDTO.getUserId() + " id number.";
            log.error(message);
            throw new UserNotFoundException(message);
        }
    }

    @Cacheable(cacheNames = "all_mails_cache")
    @Override
    public List<MailDTO> findAll() {
        List<Mail> mailList = mailRepository.findAll();
        log.info("all mails have been pulled from database.");
        return MappingHelper.mapList(mailList, MailDTO.class);
    }

    @Override
    public MailDTO findOne(Long id) {
        Optional<Mail> mail = mailRepository.findById(id);
        if (mail.isPresent() && mail.get().isActive()) {
            log.info("mail with {} has been pulled from database", id);
            return MappingHelper.map(mail.get(), MailDTO.class);
        } else {
            String message = "Mail not found by given " + id + " id number.";
            log.error(message);
            throw new MailNotFoundException(message);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Mail> mail = mailRepository.findById(id);
        if (mail.isPresent() && mail.get().isActive()) {
            mail.get().setActive(false);
            log.info("Mail with id number {} has been soft deleted", id);
        } else {
            String message = "There is no mail in the database with " + id + " id number";
            log.error(message);
            throw new MailNotFoundException(message);
        }
    }

    @CacheEvict(cacheNames = "all_mails_cache", allEntries = true)
    public void clearCache() {
        log.info("all mails cache has been cleared.");
    }

    @Override
    @KafkaListener(topics = "myTopic", groupId = "testGroupId")
    public void sendMailToAllUsers(@Payload String mailDTO) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        MailDTO receivedDto = mapper.readValue(mailDTO.getBytes(), MailDTO.class);
        List<User> userList = userRepository.findAll();
        userList.stream()
                .forEach(user -> {
                    receivedDto.setId(null);
                    receivedDto.setUserId(user.getId());
                    this.save(receivedDto);
                });
    }
}
