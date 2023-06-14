package spring.angular.blogging.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.angular.blogging.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
