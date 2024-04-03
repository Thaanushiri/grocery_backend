package abc.efg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import abc.efg.model.user;
@Repository
public interface userrepo extends JpaRepository<user, Integer> {    
}