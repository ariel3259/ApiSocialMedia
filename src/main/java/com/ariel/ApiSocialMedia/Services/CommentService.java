package com.ariel.ApiSocialMedia.Services;

import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository cr;

    public List<Comment> getAllCommentsOfPost(int idPost){
        return cr.getAllCommentsOfPost(idPost);
    }

    public List<Comment> getAllCommentsOfUser(int idUser){
        return cr.getAllCommentsOfUser(idUser);
    }

    public int saveComment(Comment comment){
        return cr.saveComment(comment);
    }

    public int updateComment(Comment comment, int id){
        return cr.editComment(comment, id);
    }

    public int deleteComment(int id){
        return cr.deleteComment(id);
    }
}
