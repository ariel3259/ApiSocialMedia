package com.ariel.ApiSocialMedia.Services;

import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository pr;

    public List<Post> getPosts(){
        return pr.getAllPosts();
    }

    public int savePost(Post post){
        return pr.savePost(post);
    }

    public int updatePost(Post post, int id){
        return pr.updatePost(post, id);
    }

    public int deletePost(int id){
        return pr.deletePost(id);
    }

    public List<Post> getPostsOfUser(int idUser){
        return pr.getAllPostOfUser(idUser);
    }
}
