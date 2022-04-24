package com.ariel.ApiSocialMedia.Repositories;

import com.ariel.ApiSocialMedia.Model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public List<Comment> getAllCommentsOfPost(int idPost){
        String query = "SELECT c.id, c.id_user, c.id_post, c.text FROM `comments` c INNER JOIN `post` p ON c.id_post = p.id WHERE c.id_post = ? ";
        Object[] params = {idPost};
        return jdbc.query(query, BeanPropertyRowMapper.newInstance(Comment.class), params);
    }

    public List<Comment> getAllCommentsOfUser(int idUser){
        String query = "SELECT c.id, c.id_user, c.id_post, c.text FROM `comments` c INNER JOIN `user` u ON c.id_user = u.id WHERE c.id_user = ? ";
        Object[] params = {idUser};
        return jdbc.query(query, BeanPropertyRowMapper.newInstance(Comment.class), params);
    }

    public int saveComment(Comment comment){
        String query = "INSERT INTO `comments`(`id_user`, `id_post`, `text`) VALUE(?, ?, ?)";
        Object[] params = {comment.getIdUser(), comment.getIdPost(), comment.getText()};
        return jdbc.update(query, params);
    }

    public int editComment(Comment comment, int id){
        String query = "UPDATE `comments` SET `text` = ? WHERE `id` = ?";
        Object[] params = {comment.getText(), id};
        return jdbc.update(query, params);
    }

    public int deleteComment(int id){
        String query = "DELETE FROM `comments` WHERE `id` = ?";
        Object[] params = {id};
        return jdbc.update(query, params);
    }
}
