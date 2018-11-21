package stream.news.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import stream.news.models.User;

import java.util.List;

public interface UserService {
	boolean authenticate(String userName, String password);
	
	List<User> findAll();
	Page<User> findAll(Pageable pageable);
	User findByUserName(String userName);
	User findById(Integer id);
	User add(User user);
	User edit(User user);
	void deleteById(Integer id);
}
