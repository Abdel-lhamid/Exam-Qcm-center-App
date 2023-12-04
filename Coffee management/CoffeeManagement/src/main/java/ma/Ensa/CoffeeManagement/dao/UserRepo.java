package ma.Ensa.CoffeeManagement.dao;

import ma.Ensa.CoffeeManagement.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

}
