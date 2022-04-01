package me.sekayasin.lab1.repo;

import me.sekayasin.lab1.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
//    List<PostV2> findAllV2();
//    void delete(long id);
}
