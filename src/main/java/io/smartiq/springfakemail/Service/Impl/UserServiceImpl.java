package io.smartiq.springfakemail.Service.Impl;

import io.smartiq.springfakemail.DTO.UserDTO;
import io.smartiq.springfakemail.Model.User;
import io.smartiq.springfakemail.Repository.IUser;
import io.smartiq.springfakemail.Service.IUserService;
import io.smartiq.springfakemail.Util.MappingHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {
    private final IUser iUser;
    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = MappingHelper.map(userDTO, User.class);
        User result = iUser.save(user);
        return MappingHelper.map(result, UserDTO.class);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        //TODO
        return null;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> userList = iUser.findAll();
        return MappingHelper.mapList(userList, UserDTO.class);
    }

    @Override
    public UserDTO findOne(Long id) {
        Optional<User> user = iUser.findById(id);
        return MappingHelper.map(user.get(), UserDTO.class);
    }

    @Override
    public void delete(Long id) {
        User user = iUser.getById(id);
        iUser.delete(user);
    }
}