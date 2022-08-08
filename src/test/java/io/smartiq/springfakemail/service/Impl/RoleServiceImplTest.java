package io.smartiq.springfakemail.service.Impl;

import io.smartiq.springfakemail.dto.UserDTO;
import io.smartiq.springfakemail.model.User;
import io.smartiq.springfakemail.repository.RoleRepository;
import io.smartiq.springfakemail.repository.UserRepository;
import io.smartiq.springfakemail.util.MappingHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private RoleServiceImpl underTest;
    @BeforeEach
    void setUp() {
        underTest = new RoleServiceImpl(roleRepository, userRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveNewRole() {
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
    }

    @Test
    void addRoleToUser() {
    }
}