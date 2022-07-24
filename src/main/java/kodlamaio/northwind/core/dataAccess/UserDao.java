package kodlamaio.northwind.core.dataAccess;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.northwind.core.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	Optional<User> findByUserName(String userName);
}
