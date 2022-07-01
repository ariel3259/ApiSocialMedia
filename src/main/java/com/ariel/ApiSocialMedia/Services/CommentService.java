package com.ariel.ApiSocialMedia.Services;

import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Repositories.CommentRepository;
import com.ariel.ApiSocialMedia.Repositories.PostRepository;
import com.ariel.ApiSocialMedia.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired 
    private UserRepository userRepo;

    public Page<Comment> getAllCommentsOfPost(long idPost, int index){
        Pageable page = PageRequest.of(index - 1, 10); 
        return commentRepo.findByPostId(idPost, page);
    }

    public Page<Comment> getAllCommentsOfUser(long idUser, int index){
        Pageable page = PageRequest.of(index - 1, 10);
        return commentRepo.findByUserId(idUser, page);
    }

    public boolean saveComment(Comment comment, long idPost, long idUser ){
        Optional<Post> post = postRepo.findById(idPost);
        Optional<Users> user = userRepo.findById(idUser);
        if(post.isEmpty() || user.isEmpty()) return false;
        comment.setPost(post.get());
        comment.setUser(user.get());
        commentRepo.save(comment);
        return true;
    }

    public boolean updateComment(Comment comment, long idPost, long idUser, long id){
        Optional<Post> post = postRepo.findById(idPost);
        Optional<Users> user = userRepo.findById(idUser);
        if(post.isEmpty() || user.isEmpty() || !commentRepo.existsById(id)) return false;
        comment.setPost(post.get());
        comment.setUser(user.get());
        comment.setId(id);
        commentRepo.save(comment);
        return true;
    }

    public boolean deleteComment(long id){
        if(!commentRepo.existsById(id)) return false;
        commentRepo.deleteById(id);
        return true;
    }
}
