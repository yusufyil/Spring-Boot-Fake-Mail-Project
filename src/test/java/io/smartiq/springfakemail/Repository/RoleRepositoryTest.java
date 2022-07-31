package io.smartiq.springfakemail.Repository;

import io.smartiq.springfakemail.Model.Mail;
import io.smartiq.springfakemail.Model.Role;
import io.smartiq.springfakemail.Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository underTest;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByNameTest() {
        //given
        User tempUser = new User(
                1L,
                "TestName",
                "TestSurname",
                "TestUsername",
                "TestPassword",
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                null
        );
        userRepository.save(tempUser);
        Role testRole = new Role(
                1L,
                "ROLE_TEST",
                LocalDateTime.now(),
                LocalDateTime.now(),
                true
        );
        Role result = underTest.save(testRole);
        //when
        Role expected = underTest.findByName(testRole.getName());
        //then
        assertThat(result).isEqualTo(expected);
    }
}