package stream.news.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import stream.news.models.Post;

import java.util.List;

/**
 * Create the interface UserRepository.
 * Note that you will not provide any implementation for it.
 * Spring Data JPA will implement it for you.
 * This is part of the "magic" behind the "Spring Data" framework.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "SELECT p.* FROM posts p ORDER BY p.date DESC", nativeQuery = true)
    List<Post> findLates5Posts(Pageable pageable);

    @Query(value = "SELECT p.* FROM posts p ORDER BY p.date DESC", nativeQuery = true)
    List<Post> findAll();
}