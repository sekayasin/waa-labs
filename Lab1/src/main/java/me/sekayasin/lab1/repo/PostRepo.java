package me.sekayasin.lab1.repo;

import me.sekayasin.lab1.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {
//    List<PostV2> findAllV2();

    List<Post> findPostsByTitle(String titleKeyWord);
}
