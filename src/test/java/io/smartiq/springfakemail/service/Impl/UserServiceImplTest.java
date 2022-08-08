package io.smartiq.springfakemail.service.Impl;

import io.smartiq.springfakemail.dto.UserDTO;
import io.smartiq.springfakemail.exception.User.UserNotFoundException;
import io.smartiq.springfakemail.exception.User.UsernameAlreadyTakenException;
import io.smartiq.springfakemail.model.User;
import io.smartiq.springfakemail.repository.UserRepository;
import io.smartiq.springfakemail.util.MappingHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    void canLoadUserByUsername() {
    }

    @Test
    void canSaveUser() {
        //given
        UserDTO testUserDTO = new UserDTO(
                1L,
                "TestName",
                "TestSurname",
                "TestUsername",
                "TestPassword",
                true,
                null
        );
        User testUser = MappingHelper.map(testUserDTO, User.class);
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(passwordEncoder.encode("TestPassword")).thenReturn("TestPassword");
        //when
        UserDTO userDTO = underTest.save(testUserDTO);
        //then
        assertThat(userDTO.getUsername()).isEqualTo("TestUsername");
        verify(userRepository).save(testUser);
    }

    @Test
    void canSaveAlreadySavedUser() {
        //given
        UserDTO testUserDTO = new UserDTO(
                1L,
                "TestName",
                "TestSurname",
                "TestUsername",
                "TestPassword",
                true,
                null
        );
        User testUser = MappingHelper.map(testUserDTO, User.class);
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(userRepository.findByUsername("TestUsername")).thenReturn(testUser);
        when(passwordEncoder.encode("TestPassword")).thenReturn("TestPassword");
        //when & then
        Assertions.assertThrows(UsernameAlreadyTakenException.class, () -> underTest.save(testUserDTO));
    }

    @Test
    void updateNotExistingUser() {
        //given
        UserDTO testUserDTO = new UserDTO(
                1L,
                "TestName",
                "TestSurname",
                "TestUsername",
                "TestPassword",
                true,
                null
        );
        User testUser = MappingHelper.map(testUserDTO, User.class);
        when(userRepository.save(testUser)).thenReturn(testUser);
        when(userRepository.findByUsername("TestUsername")).thenReturn(null);
        when(passwordEncoder.encode("TestPassword")).thenReturn("TestPassword");
        //when & then
        Assertions.assertThrows(UserNotFoundException.class, () -> underTest.update(testUserDTO));
    }

    @Test
    void canGetAllUsers() {
        //when
        underTest.findAll();
        //then
        verify(userRepository).findAll();
    }

    @Test
    void findOneActiveUserById() {
        //given
        User user = new User(
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
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //when
        UserDTO expected = underTest.findOne(1L);
        //then
        assertThat(1L).isEqualTo(expected.getId());
        verify(userRepository).findById(1L);
    }


    @Test
    void softDeleteActiveUserById() {
        //given
        User user = new User(
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
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //when
        underTest.delete(user.getId());
        //then
        assertThat(user.isActive()).isFalse();
    }

    @Test
    void softDeleteAlreadyDeletedUserById() {
        //given
        User user = new User(
                1L,
                "TestName",
                "TestSurname",
                "TestUsername",
                "TestPassword",
                LocalDateTime.now(),
                LocalDateTime.now(),
                false,
                null
        );
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        //when & then
        Assertions.assertThrows(UserNotFoundException.class, () -> underTest.delete(user.getId()));
    }
}