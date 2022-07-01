package com.ariel.ApiSocialMedia.Repositories;


import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ariel.ApiSocialMedia.Model.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	public Page<Post> findByStateIsTrue(Pageable pageable);
	
	@Query( value = "SELECT * FROM post WHERE id = :id AND state = true LIMIT 10", nativeQuery = true)
	public Optional<Post> findById(@Param("id") Long id);

	@Modifying
	@Query( value = "UPDATE post SET state = false WHERE id = :id", nativeQuery = true)
	public void deleteById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE post SET state = false WHERE user_id = :idUser AND state = true", nativeQuery = true)
	public void deleteByUserId(@Param("idUser") long idUser);

	@Query(value = "SELECT * FROM post WHERE user_id = :idUser AND state = true", nativeQuery = true)
	public Page<Post> findByUserId(long idUser, Pageable pageable);
}
