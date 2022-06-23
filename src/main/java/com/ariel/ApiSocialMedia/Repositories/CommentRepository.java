package com.ariel.ApiSocialMedia.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Model.Users;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    public List<Comment> findByUser(Users user);
    public List<Comment> findByPost(Post post);
}
