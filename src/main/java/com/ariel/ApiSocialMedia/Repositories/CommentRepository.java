package com.ariel.ApiSocialMedia.Repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Model.Users;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


    public Page<Comment> findByUser(Users user, Pageable page);

    public Page<Comment> findByPost(Post post, Pageable page);
}
