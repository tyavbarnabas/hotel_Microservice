package com.codeafrica.userservice.serviceImpl;

import com.codeafrica.userservice.dtos.UserDto;
import com.codeafrica.userservice.exception.ResourceNotFoundException;
import com.codeafrica.userservice.models.User;
import com.codeafrica.userservice.repository.UserRepository;
import com.codeafrica.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User with given id not found on server!!" + id));
    }

    @Override
    public User createUser(UserDto userDto) {

        Optional<User> userByEmail = userRepository.findByEmail(userDto.getEmail());
        if(userByEmail.isPresent()){
            throw new RuntimeException("User with the email already exist try login");
        }
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setEmail(userDto.getEmail());
        newUser.setAddress(userDto.getAddress());
        return userRepository.save(newUser);

    }

    @Override
    public void DeleteUser(Long id) {
        boolean exist = userRepository.existsById(id);
        if (!exist){
            throw new IllegalStateException("User with id" + id + "does not exist");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, String name, String email, String address) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException(
                "User not found " + id + "does not exist"));
        if(name != null && name.length() > 0 && !Objects.equals(user.getName(),name)){
            user.setName(name);
        }
        if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(),email)){
            Optional<User>optionalUser = userRepository.findByEmail(email);
            if(optionalUser.isPresent()){
                throw new IllegalStateException("email already in use");
            }
            user.setEmail(email);
        }
    }


}
