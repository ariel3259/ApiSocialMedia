package com.ariel.ApiSocialMedia.Services;

import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Repositories.CommentRepository;
import com.ariel.ApiSocialMedia.Repositories.PostRepository;
import com.ariel.ApiSocialMedia.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired 
    private UserRepository userRepo;

    public List<Comment> getAllCommentsOfPost(long idPost){
        Post post = postRepo.findById(idPost).get();
        return commentRepo.findByPost(post);
    }

    public List<Comment> getAllCommentsOfUser(long idUser){
        Users user = userRepo.findById(idUser).get();
        return commentRepo.findByUser(user);
    }

    public boolean saveComment(Comment comment, long idPost, long idUser ){
        Post post = postRepo.findById(idPost).get();
        Users user = userRepo.findById(idUser).get();
        if(post == null || user == null) return false;
        comment.setPost(post);
        comment.setUser(user);
        commentRepo.save(comment);
        return true;
    }

    public boolean updateComment(Comment comment, long idPost, long idUser){
        Post post = postRepo.findById(idPost).get();
        Users user = userRepo.findById(idUser).get();
        if(post == null || user == null) return false;
        comment.setPost(post);
        comment.setUser(user);
        commentRepo.save(comment);
        return true;
    }

    public boolean deleteComment(long id){
        if(!commentRepo.existsById(id)) return false;
        commentRepo.deleteById(id);
        return true;
    }
}
