package io.smartiq.springfakemail.Service.Impl;

import io.smartiq.springfakemail.DTO.UserDTO;
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
        if(user == null){
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
        User result = userRepository.save(user);
        log.info("{} {} saved to database.", user.getName(), user.getUsername());
        return MappingHelper.map(result, UserDTO.class);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        log.error("update part has not been finished yet!");
        //TODO
        return null;
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
        log.info("{} {} named user has been pulled from database.");
        return MappingHelper.map(user.get(), UserDTO.class);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.getById(id);
        log.warn("{} {} named user has been deleted permanently!");
        userRepository.delete(user);
    }
}
