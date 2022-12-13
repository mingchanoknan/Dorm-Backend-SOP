package com.dorm.user.userservice.service;


import com.dorm.user.userservice.pojo.User;
import com.dorm.user.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCrypt encoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getUser(){
        return userRepository.findAll();
    }

    public boolean addUser(User user){
        try {
            userRepository.insert(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateUser(User user){
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean deleteUser(String id){
        try {
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public User getRoomByNumber (String room_number) {
        try {
            return userRepository.findByRoomNumber(room_number);
        } catch (Exception e) {
            return null;
        }
    }

    public User getUserByUsername(String username) {
        try {
            return  userRepository.findByUsername(username);
        }
        catch (Exception e) {
            return null;
        }
    }

    public int countUser() {
        return (int) userRepository.count();
    }
     public User getUserById(String id) throws Exception {
        try {
            Optional<User> user = userRepository.findById(id);
            if( user!=null ){
                return user.get();
            }
            else {
                return null;
            }
        }catch (Exception e){
            throw new Exception(e);
        }


     }
}

