package com.ariel.ApiSocialMedia.Repositories;

import com.ariel.ApiSocialMedia.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Post> getAllPosts(){
        String query = "SELECT * FROM `posts`";
        List<Post> posts = jdbc.query(query, BeanPropertyRowMapper.newInstance(Post.class));
        return posts;
    }

    public int savePost(Post post){
        String query = "INSERT INTO `posts`(`title`, `body`, `id_user`) VALUE(?, ?, ?)";
        Object[] params = {post.getTitle(), post.getBody(), post.getIdUser()};
        int result = jdbc.update(query, params);
        return result;
    }

    public int updatePost(Post post, int id){
        String query = "UPDATE `posts` SET `title` = ?, `body` = ?, `id_user` = ? WHERE `id` = ? ";
        Object[] params = {post.getTitle(), post.getBody(), post.getIdUser(), id};
        int result = jdbc.update(query, params);
        return result;
    }

    public int deletePost(int id){
        String query = "DELETE FROM `posts` WHERE `id` = ? ";
        Object[] params = { id };
        int result = jdbc.update(query, params);
        return result;
    }

    public List<Post> getAllPostOfUser(int idUser){
        String query = "SELECT p.id, p.title, p.body, p.id_user FROM `posts` p INNER JOIN `users` u ON p.id_user = u.id WHERE p.id_user = ?";
        Object[] params = { idUser };
        return jdbc.query(query, BeanPropertyRowMapper.newInstance(Post.class), params);
    }
}
