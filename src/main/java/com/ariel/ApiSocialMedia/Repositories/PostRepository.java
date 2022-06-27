package com.ariel.ApiSocialMedia.Repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ariel.ApiSocialMedia.Model.Post;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	@Query( value = "SELECT * FROM post WHERE state = true", nativeQuery = true)
	public List<Post> findAll();
	
	@Query( value = "SELECT * FROM post WHERE id = :id AND state = true", nativeQuery = true)
	public Optional<Post> findById(@Param("id") Long id);

	@Modifying
	@Query( value = "UPDATE post SET state = false WHERE id = :id", nativeQuery = true)
	public void deleteById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE post SET state = false WHERE user_id = :idUser AND state = true", nativeQuery = true)
	public void deleteByUserId(@Param("idUser") long idUser);

	@Query(value = "SELECT * FROM post WHERE user_id = :idUser AND state = true", nativeQuery = true)
	public List<Post> findByUserId(long idUser);
}
