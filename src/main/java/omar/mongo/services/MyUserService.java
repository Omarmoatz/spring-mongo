package omar.mongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import omar.mongo.repositories.UserRepository;
import omar.mongo.models.User;

@Service
public class MyUserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found with email" + email));
    }
}
