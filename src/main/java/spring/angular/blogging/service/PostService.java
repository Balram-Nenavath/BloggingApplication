package spring.angular.blogging.service;
import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.angular.blogging.exception.PostNotFoundException;
import spring.angular.blogging.model.Post;
import spring.angular.blogging.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    

    @Autowired
    private AuthService authService;

    @Transactional
    public List<Post> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().collect(toList());
    }

    @Transactional
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public Post readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return post;
    }

	
	public void deleteSinglePost(Long id) {
		postRepository.deleteById(id);
		
	}

}
