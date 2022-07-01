package com.ariel.ApiSocialMedia.Repositories;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ariel.ApiSocialMedia.Model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT * FROM comment WHERE user_id = :idUser", nativeQuery = true) 
    public Page<Comment> findByUserId(@Param("idUser") long idUser, Pageable page);

    @Query(value = "SELECT * FROM comment WHERE post_id = :idPost", nativeQuery = true)
    public Page<Comment> findByPostId(@Param("idPost") long idPost, Pageable page);
}
