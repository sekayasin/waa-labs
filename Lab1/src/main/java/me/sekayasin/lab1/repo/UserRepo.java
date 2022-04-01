package me.sekayasin.lab1.repo;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

//    @Query(value = "SELECT * FROM post where user_id = ?1", nativeQuery = true)
//    @Query("select p from Post p  join p.author u where u.id = ?1")
//    List<Post> findAllPostsByUserId(long id);

}
