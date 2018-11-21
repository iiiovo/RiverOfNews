package stream.news.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stream.news.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
}
