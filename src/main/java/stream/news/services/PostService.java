package stream.news.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stream.news.models.Post;

import java.util.List;

/**
 * Services – hold the business logic
 */
public interface PostService {
	List<Post> findAll();
	Page<Post> findAll(Pageable pageable);
	List<Post> findLatest5();
	Post findById(Integer id);
	Post add(Post post);
	Post edit(Post post);
	void deleteById(Integer id);
}
