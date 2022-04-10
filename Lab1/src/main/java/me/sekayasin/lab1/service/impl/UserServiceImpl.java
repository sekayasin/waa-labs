package me.sekayasin.lab1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.Role;
import me.sekayasin.lab1.domain.User;
import me.sekayasin.lab1.domain.dto.*;
import me.sekayasin.lab1.repo.RoleRepo;
import me.sekayasin.lab1.repo.UserRepo;
import me.sekayasin.lab1.service.UserService;
import me.sekayasin.lab1.util.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final ListMapper<User, UserDto> listMapperUserDto;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User myUser = userRepo.findByUsername(username);
      if (myUser == null) {
          log.error("User not found in the database");
      } else {
          log.info("User found in the database: {}", username);
      }
      Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
      myUser.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

      return new org.springframework.security.core.userdetails.User(myUser.getUsername(), myUser.getPassword(), authorities);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        if (!userRepo.existsById(id))
            throw new IllegalStateException("User with Id " + id + " does not exist");
        return modelMapper.map(userRepo.getById(id), UserDto.class);
    }

    @Override
    public void save(UserNameDto user) {
        if (userRepo.findByUsername(user.getUsername()) != null)
            throw new IllegalStateException("Username: " + user.getUsername() + " exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(modelMapper.map(user, User.class));
    }

    @Override
    public void addPostByUserId(long id, PostDto postDto) {
        if (!userRepo.existsById(id))
            throw new IllegalStateException("User with Id " + id + " does not exist");
        User userToAddPost = userRepo.getById(id);
        Post newPost = modelMapper.map(postDto, Post.class);
        userToAddPost.getPosts().add(newPost);
        userRepo.save(userToAddPost);
    }

    @Override
    public List<PostResponseDto> findAllPostsByUserId(long id) {
        if (!userRepo.existsById(id))
            throw new IllegalStateException("User with Id " + id + " does not exist");
        return userRepo.getById(id).getPosts()
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(long id) {
        if (!userRepo.existsById(id))
            throw new IllegalStateException("User with Id " + id + " does not exist");
        userRepo.deleteById(id);
    }

    @Override
    public List<UserDto> findAllUsersWithMoreThanNPosts(int numberOfPosts) {
        return (List<UserDto>) listMapperUserDto.mapList(userRepo.findAllUsersWithMoreThanNPosts(numberOfPosts), new UserDto());

//        return userRepo.findAll()
//                .stream()
//                .filter(user -> user.getPosts().size() > numberOfPosts)
//                .map(user -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getUserCommentOnPostById(long userId, long postId, long commentId) {
        if (!userRepo.existsById(userId))
            throw new IllegalStateException("User with Id " + userId + " does not exist");

        List<Post> postList = userRepo.getById(userId).getPosts();

        List<Comment> commentList = postList.stream()
                .filter(post -> post.getId() == postId)
                .map(Post::getComments)
                .collect(Collectors.toList())
                .stream().flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (Comment comment : commentList) {
            if (comment.getId() == commentId)
                return modelMapper.map(comment, CommentDto.class);
        }
        return null;
    }

    @Override
    public List<Comment> getUserCommentsOnPost(long userId, long postId) {
        if (!userRepo.existsById(userId))
            throw new IllegalStateException("User with Id " + userId + " does not exist");
        return userRepo.getById(userId).getPosts()
                .stream()
                .filter(post -> post.getId() == postId)
                .map(Post::getComments)
                .collect(Collectors.toList())
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findUsersByPostTitle(String title) {
        return (List<UserDto>) listMapperUserDto.mapList(userRepo.findUsersByPostTitle(title), new UserDto());
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }
}
