package io.smartiq.springfakemail.Service.Impl;

import io.smartiq.springfakemail.DTO.UserDTO;
import io.smartiq.springfakemail.Exception.User.UserNotFoundException;
import io.smartiq.springfakemail.Exception.User.UsernameAlreadyTakenException;
import io.smartiq.springfakemail.Model.User;
import io.smartiq.springfakemail.Repository.UserRepository;
import io.smartiq.springfakemail.Service.IUserService;
import io.smartiq.springfakemail.Util.MappingHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User with the {} not found in the database.", username);
            throw new UsernameNotFoundException("user not found in the database.");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = MappingHelper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User isAlreadySaved = userRepository.findByUsername(user.getUsername());
        if (isAlreadySaved == null) {
            User result = userRepository.save(user);
            log.info("{} {} saved to database.", user.getName(), user.getUsername());
            return MappingHelper.map(result, UserDTO.class);
        } else {
            String message = "Given username is already taken.";
            log.error(message);
            throw new UsernameAlreadyTakenException(message);
        }
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        if(userRepository.findByUsername(userDTO.getUsername()) == null){
            String message = "There is no user in database with " + userDTO.getUsername() + " username.";
            log.error(message);
            throw new UserNotFoundException(message);
        }
        User user = MappingHelper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setId(userRepository.findByUsername(userDTO.getUsername()).getId());
        User result = userRepository.save(user);
        log.info("User with " + result.getUsername() + " username updated.");
        return MappingHelper.map(result, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = userRepository.findAll();
        log.info("all users have been pulled from database.");
        return MappingHelper.mapList(userList, UserDTO.class);
    }

    @Override
    public UserDTO findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info("{} {} named user has been pulled from database.", user.get().getName(), user.get().getSurname());
            return MappingHelper.map(user.get(), UserDTO.class);
        } else {
            String message = "User not found by given " + id + " id number.";
            log.error(message);
            throw new UserNotFoundException(message);
        }
    }

    @Override
    public void delete(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && user.get().isActive()) {
            user.get().setActive(false);
            log.info("User with id number {} has been soft deleted", id);
        } else {
            String message = "There is no user in the database with " + id + " id number.";
            log.error(message);
            throw new UserNotFoundException(message);
        }
    }
}
