package com.daitagroup.mysql.services;

import com.daitagroup.mysql.Daos.UserRepository;
import com.daitagroup.mysql.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class CRUDServiceImp implements CRUDService {

    @Autowired
    UserRepository userRepository;

    public CRUDServiceImp() {
    }

    public void addOrUpdateUser(@Valid User user){
        userRepository.save(user);
    }

    public void deleteUser(@Valid User user){
        userRepository.delete(user);
    }

    public User getUser(Integer id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
}
