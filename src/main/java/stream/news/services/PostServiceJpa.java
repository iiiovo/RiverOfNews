package stream.news.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import stream.news.models.Post;
import stream.news.repositories.PostRepository;

import java.util.List;

/**
 * Implement the PostService and UserService to Use the DB
 * 	Just add new implementations for the UserService and PostService, 
 * 	annotated with @Primary. 
 * 	This will tell the Spring Framework to use these implementations instead of the old stubs. 
 *
 */
@Service
@Primary
public class PostServiceJpa implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return this.postRepository.findAll();
	}
	@Override
	public Page<Post> findAll(Pageable pageable){
		return this.postRepository.findAll(pageable);
	}
	@Override
	public List<Post> findLatest5() {
		// Create our own query
		return this.postRepository.findLates5Posts( PageRequest.of(0,5) );
	}

	@Override
	public Post findById(Integer id) {
		return this.postRepository.findById(id).orElse(null);
	}
	@Override
	public Post add(Post post) {
		return this.postRepository.save(post);
	}
	@Override
	public Post edit(Post post) {
		return this.postRepository.save(post);
	}
	@Override
	public void deleteById(Integer id) {
		this.postRepository.deleteById(id);
	}
}
