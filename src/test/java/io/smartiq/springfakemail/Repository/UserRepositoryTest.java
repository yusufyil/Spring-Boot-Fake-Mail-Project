package io.smartiq.springfakemail.Repository;

import io.smartiq.springfakemail.Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByUsernameTest() {
        //given
        User testUser = new User(
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
        User result = underTest.save(testUser);
        //when
        User expected = underTest.findByUsername(testUser.getUsername());
        //then
        assertThat(result).isEqualTo(expected);
    }
}