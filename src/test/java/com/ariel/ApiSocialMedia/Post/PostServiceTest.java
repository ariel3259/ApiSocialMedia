package com.ariel.ApiSocialMedia.Post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Repositories.PostRepository;
import com.ariel.ApiSocialMedia.Repositories.UserRepository;
import com.ariel.ApiSocialMedia.Services.PostService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
    
    @InjectMocks
    private PostService service;

    @Mock
    private UserRepository userRepo;

    @Mock
    private PostRepository postRepo;

    private final Post post = new Post("title", "body");
    
    private final List<Post> posts = new ArrayList<>();

    private final Users user = new Users(1l, "Jon", "Doe", "Xx_jony_xX", "1234", true);


    @Before
    public void init(){
        posts.add(post);
    }

    /* 
    @Test
    public void GET_POSTS(){
        when(postRepo.findAll()).thenReturn(posts);
        List<Post> postsFound = service.getPosts();
        assertNotNull(postsFound);
        assertEquals(posts, postsFound);
    }

    @Test
    public void GET_POSTS_EMPTY(){
        when(postRepo.findAll()).thenReturn(new ArrayList<>());
        List<Post> postsFound = service.getPosts();
        assertNotNull(postsFound);
        assertNotEquals(posts, postsFound);
    }
    */

    @Test
    public void SAVE_POST(){
        when(userRepo.getById(user.getId())).thenReturn(user);
        when(userRepo.findById(user.getId())).thenReturn(Optional.of(user));
        when(postRepo.save(post)).thenReturn(post);
        boolean result = service.savePost(post, user.getId());
        assertTrue(result);
    }

    @Test
    public void SAVE_POST_WITH_NO_USER(){
        when(userRepo.getById(user.getId())).thenReturn(null);
        boolean result = service.savePost(post, user.getId());
        assertFalse(result);
    }

    @Test
    public void DELETE_POST(){
        when(postRepo.getById(post.getId())).thenReturn(post);
        boolean result = service.deletePost(post.getId());
        assertTrue(result);
    }
    
    @Test
    public void DELETE_POST_WITH_NO_POST(){
        when(postRepo.getById(post.getId())).thenReturn(null);
        boolean result = service.deletePost(post.getId());
        assertFalse(result);
    }

    @Test
    public void DELETE_POST_OF_USER(){
        when(userRepo.getById(user.getId())).thenReturn(user);
        boolean result = service.deletePostOfUser(user.getId());
        assertTrue(result);
    }

    @Test
    public void DELETE_POST_OF_USER_WITH_NO_USER(){
        when(userRepo.getById(user.getId())) .thenReturn(null);
        boolean result = service.deletePostOfUser(user.getId());
        assertFalse(result);
    }

    /* 
    @Test
    public void GET_POSTS_OF_USER(){
        when(userRepo.getById(user.getId())).thenReturn(user);
        when(postRepo.findByUserId(user.getId())).thenReturn(posts);
        List<Post> postsFound = service.getPostsOfUser(user.getId());
        assertEquals(posts, postsFound);
    }

    @Test
    public void GET_POSTS_OF_USER_WITH_NO_USER(){
        when(userRepo.getById(user.getId())).thenReturn(null);
        List<Post> postsFound = service.getPostsOfUser(user.getId());
        assertNull(postsFound);
    }

    @Test
    public void GET_POSTS_OF_USER_EMPTY(){
        when(userRepo.getById(user.getId())).thenReturn(user);
        when(postRepo.findByUserId(user.getId())).thenReturn(new ArrayList<>());
        List<Post> postsFound = service.getPostsOfUser(user.getId());
        assertNotEquals(posts, postsFound);
    }*/
}



