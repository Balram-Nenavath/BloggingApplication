package spring.angular.blogging.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import spring.angular.blogging.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);

    @Query(value = "SELECT * FROM blog_user WHERE user_name = ?1", nativeQuery = true)
    User findByName(String uname);
}
