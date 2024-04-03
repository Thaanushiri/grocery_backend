package abc.efg.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import abc.efg.model.user;
import abc.efg.service.userservice;

@RestController
@RequestMapping("/api")
public class apicontroller {

    @Autowired
    private userservice us;

    @PostMapping("/user")
    public ResponseEntity<user> createUser(@RequestBody user us1){
        user createdUser = us.createUser(us1);
        if (createdUser != null) {
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<user>> getAllUsers(){
        List<user> users = us.getAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<user> getUserById(@PathVariable int userId){
        Optional<user> user1 = us.getUserById(userId);
        if (user1.isPresent()) {
            return new ResponseEntity<>(user1.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<user> updateUser(@PathVariable int userId, @RequestBody user updatedUser) {
        Optional<user> existingUserOptional = us.getUserById(userId);
        if (existingUserOptional.isPresent()) {
            user existingUser = existingUserOptional.get();
            existingUser.setName(updatedUser.getName()); // assuming you have a setName method in your user class
            // Similarly, update other fields as needed
            user updatedUserEntity = us.updateUser(existingUser);
            return new ResponseEntity<>(updatedUserEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        Optional<user> existingUserOptional = us.getUserById(userId);
        if (existingUserOptional.isPresent()) {
            us.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}