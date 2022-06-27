package com.ariel.ApiSocialMedia.Repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ariel.ApiSocialMedia.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE user_id = :idUser", nativeQuery = true) 
    public List<Comment> findByUserId(@Param("idUser") long idUser);

    @Query(value = "SELECT * FROM comment WHERE post_id = :idPost", nativeQuery = true)
    public List<Comment> findByPostId(@Param("idPost") long idPost);
}
