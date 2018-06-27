package com.daitangroup.mysql.services;

import com.daitangroup.mysql.daos.UserRepository;
import com.daitangroup.mysql.entities.User;
import com.daitangroup.mysql.exception.UserAlreadyExistException;
import com.daitangroup.mysql.exception.UserIdMissingException;
import com.daitangroup.mysql.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private static String USER_ALREADY_EXIST = "User already exist";
    private static String USER_NOT_FOUND = "User not found";
    private static String USER_ID_MISSED = "User id is missed";

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        if(userNameExist(user))
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);

        return userRepository.save(user);
    }

    public User updateUser(User user){
        if(user.getId() == null)
            throw new UserIdMissingException(USER_ID_MISSED);

        if(!userIdExist(user))
            throw new UserNotFoundException(USER_NOT_FOUND);

        User userFromDB = userRepository.findByName(user.getName());

        if(userFromDB != null && !userFromDB.getId().equals(user.getId()))
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);

        return userRepository.save(user);
    }

    public void deleteUser(User user){
        if(user.getId() == null){
            throw new UserIdMissingException(USER_ID_MISSED);
        }

        if(!userIdExist(user)){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }

        userRepository.delete(user);
    }

    public User getUser(String id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent())
            throw new UserNotFoundException(USER_NOT_FOUND);

        return user.get();
    }

    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();

        if(users == null && users.isEmpty())
            throw new UserNotFoundException(USER_NOT_FOUND);

        return users;
    }

    public List<User> getAllUsersSortedByName(){
        List<User> users = userRepository.findAll(new Sort(Sort.DEFAULT_DIRECTION,"name"));

        if(users == null && users.isEmpty())
            throw new UserNotFoundException(USER_NOT_FOUND);

        return users;
    }

    public  User getUserByName(String name){
        User user = userRepository.findByName(name);

        if(user == null)
            throw new UserNotFoundException(USER_NOT_FOUND);

        return user;
    }

    private boolean userNameExist(User user){
        User userFromDB = userRepository.findByName(user.getName());

        if(userFromDB == null)
            return false;

        return true;
    }

    private boolean userIdExist(User user) {
        Optional<User> userOptinal = userRepository.findById(user.getId());

        if(!userOptinal.isPresent())
            return false;

        return true;
    }
}
