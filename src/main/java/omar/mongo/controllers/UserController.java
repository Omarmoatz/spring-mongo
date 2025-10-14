package omar.mongo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import omar.mongo.models.User;
import omar.mongo.services.MyUserService;


@RestController
public class UserController {

    @Autowired
    private MyUserService userService;

    @GetMapping("api/users")
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }
}
