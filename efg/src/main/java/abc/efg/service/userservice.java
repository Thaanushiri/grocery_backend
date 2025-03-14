package abc.efg.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import abc.efg.model.user;
import abc.efg.repository.userrepo;

@Service
public class userservice {

    @Autowired
    private userrepo repo;

    public user createUser(user a){
        return repo.save(a);
    }

    public List<user> getAllUsers(){ 
        return repo.findAll();
    }

    public Optional<user> getUserById(Integer userId){
        return repo.findById(userId);
    }

    public user updateUser(user updatedUser) {
        return repo.save(updatedUser);
    }

    public void deleteUser(Integer userId) {
        repo.deleteById(userId);
    }
}