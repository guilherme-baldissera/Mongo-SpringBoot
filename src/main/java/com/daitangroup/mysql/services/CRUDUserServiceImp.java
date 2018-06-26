package com.daitangroup.mysql.services;

import com.daitangroup.mysql.Daos.UserRepository;
import com.daitangroup.mysql.entities.User;
import com.daitangroup.mysql.exception.UserAlreadyExistException;
import com.daitangroup.mysql.exception.UserIdMissingException;
import com.daitangroup.mysql.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CRUDServiceImp implements CRUDService {

    @Autowired
    UserRepository userRepository;

    private static String USER_ALREADY_EXIST = "User already exist";
    private static String USER_NOT_FOUND = "User not found";
    private static String USER_ID_MISSED = "User id is missed";



    public CRUDServiceImp() {
    }

    public User addUser(User user){

            User userFromDB = userRepository.findByName(user.getName());

            if(userFromDB != null){
                throw new UserAlreadyExistException(USER_ALREADY_EXIST);
            }
            return userRepository.save(user);

    }

    public User updateUser(User user){
        if(user.getId() == null){
            throw new UserIdMissingException(USER_ID_MISSED);
        }

        User userFromDB = userRepository.findById(user.getId()).get();

        if(userFromDB == null){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }


        User userFromDB2 = userRepository.findByName(user.getName());

        if(userFromDB2 != null && !userFromDB2.getId().equals(user.getId())){
            throw new UserAlreadyExistException(USER_ALREADY_EXIST);
        }

        userFromDB.setName(user.getName());
        userFromDB.setPassword(user.getPassword());


        return userRepository.save(userFromDB);

    }

    public void deleteUser(User user){
        if(user.getId() == null){
            throw new UserIdMissingException(USER_ID_MISSED);
        }

        User userFromDB = userRepository.findById(user.getId()).get();

        if(userFromDB == null){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        userRepository.delete(userFromDB);
    }

    public User getUser(String id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException(USER_NOT_FOUND);
        return user.get();
    }

    public  User getUserByName(String name){
        User user = userRepository.findByName(name);
        if(user == null){
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        return user;

    }
}
