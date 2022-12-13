package com.dorm.user.userservice.controller;


import com.dorm.user.userservice.pojo.User;
import com.dorm.user.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    public List<User> getUser(){
        return userService.getUser();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public User login(@RequestParam String username, @RequestParam String password) throws Exception {
//        System.out.println(username+" "+ password);
     try {
         User user = userService.getUserByUsername(username);
         if (user == null) {
             throw new Exception("Cant find this username");
         }
         else {
             if (BCrypt.checkpw(password, user.getPassword())) {
                 System.out.println("Login Successfully");
                 user.setPassword("");
                 return user;
             }
             else {
                 System.out.println("Login failed");
                 return null;
             }
         }
     }
     catch (Exception e) {
         throw new Exception(e);
     }
    }
    @RequestMapping(value ="/addUser", method = RequestMethod.POST)
    public boolean addReserve(@RequestBody User user){
        try {
            User newUser = userService.getUserByUsername(user.getUsername());

            if (newUser != null) {
                System.out.println("This username is already taken");
                return false;
            }
            String passHashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(passHashed);
            userService.addUser(user);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @RequestMapping(value ="/updateUser", method = RequestMethod.POST)
    public boolean updateUser(@RequestBody User user){
        try {
            userService.updateUser(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/deleteUser/{id}", method = RequestMethod.DELETE)
    public boolean deleteUser(@PathVariable("id") String id){
        try {
            userService.deleteUser(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @RequestMapping(value ="/getUserNum/{room_number}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserNum(@PathVariable("room_number") String room_number){
        try {
            User user = userService.getRoomByNumber(room_number);
            return ResponseEntity.ok(user);
        }catch (Exception e){
            return null;
        }
    }

    @RequestMapping(value ="/countUser", method = RequestMethod.GET)
    public int countUser(){
        return  userService.countUser();
    }
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable String id) throws Exception {
        return userService.getUserById(id);
    }
}

