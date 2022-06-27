package com.ariel.ApiSocialMedia.Services;

import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Repositories.PostRepository;
import com.ariel.ApiSocialMedia.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;
    
    @Autowired 
    private UserRepository repositoryUser;

    public List<Post> getPosts(){
        return repository.findAll();
    }

    public boolean savePost(Post post, long idUser){
        Optional<Users> user = repositoryUser.findById(idUser); 
    	if(user.isEmpty()) return false;
    	post.setUser(user.get());
    	repository.save(post);
        return true;
    }

    public boolean deletePost(long id){
        if(repository.getById(id) == null) return false;
        repository.deleteById(id);
        return true;
    }

    public boolean deletePostOfUser(long idUser){
        if(repositoryUser.getById(idUser) == null) return false;
        repository.deleteByUserId(idUser);
        return true;
    }

    public List<Post> getPostsOfUser(long idUser){
        if(repositoryUser.getById(idUser) == null) return null;
        return repository.findByUserId(idUser);
    }

}
