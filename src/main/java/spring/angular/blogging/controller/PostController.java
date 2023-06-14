package spring.angular.blogging.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.angular.blogging.model.Post;
import spring.angular.blogging.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
//@CrossOrigin("http://localhost:4200")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity createPost(@RequestBody Post post) {
    	
        postService.createPost(post);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Post> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
    
//	@DeleteMapping(value = "/delete/{id}")
//	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) throws Exception {
    //		postService.deleteSinglePost(id);
    //		return ResponseEntity.ok("Post deleted with id " + id);
    //	}

    @DeleteMapping(value = "/delete/{id}")
    public String deleteEmployee(@PathVariable Long id){
    	postService.deleteSinglePost(id);
    	return "Post deleted with id " + id;
    }
}
