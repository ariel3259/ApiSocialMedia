package com.ariel.ApiSocialMedia.Repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ariel.ApiSocialMedia.Model.Users;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	//find a user
	@Query( value = "SELECT * FROM users WHERE username = :username AND state = true", nativeQuery = true)
	public Users findByUsername(@Param("username") String username);

	@Query( value = "SELECT * FROM users WHERE id = :id AND state = true", nativeQuery = true)
	public Optional<Users> findById(@Param("id") Long id);
	
	@Modifying
	@Query(value = "UPDATE users SET state = false WHERE id = :id", nativeQuery = true)
	public void deleteById(@Param("id") Long id);  
}
