package com.ariel.ApiSocialMedia.Comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Model.Users;
import com.ariel.ApiSocialMedia.Repositories.CommentRepository;
import com.ariel.ApiSocialMedia.Repositories.PostRepository;
import com.ariel.ApiSocialMedia.Repositories.UserRepository;
import com.ariel.ApiSocialMedia.Services.CommentService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {

    @InjectMocks
    private CommentService service;

    @Mock
    private CommentRepository commentRepo;

    @Mock
    private PostRepository postRepo;

    @Mock
    private UserRepository usersRepo;

    private final Comment comment = new Comment("Hi Jon");

    private final long idPost = 1;

    private final long idUser = 2;

    List<Comment> comments = new ArrayList<>();

    @Before
    public void init(){
        comments.add(comment);
    }

    /* 
    @Test
    public void GET_ALL_COMMENTS_OF_POST(){
        when(commentRepo.findByPostId(idPost)).thenReturn(comments);
        List<Comment> commentsFound = service.getAllCommentsOfPost(idPost);
        assertEquals(comments, commentsFound);
        assertNotEquals(0, commentsFound.size());
    }

    @Test
    public void GET_ALL_COMMENTS_OF_POST_BUT_COMMENTS_ARE_NULL(){
        when(commentRepo.findByPostId(idPost)).thenReturn(new ArrayList<>());
        List<Comment> commentsFound = service.getAllCommentsOfPost(idPost);
        assertEquals(0, commentsFound.size());
    }

    @Test
    public void GET_ALL_COMMENTS_OF_USER(){
        when(commentRepo.findByUserId(idUser)).thenReturn(comments);
        List<Comment> commentsFound = service.getAllCommentsOfUser(idUser);
        assertNotEquals(0, commentsFound.size());
        assertEquals(comments, commentsFound);
    }

    @Test
    public void GET_ALL_COMMENTS_OF_USER_BUT_COMMENTS_ARE_NULL(){
        when(commentRepo.findByUserId(idUser)).thenReturn(new ArrayList<>());
        List<Comment> commentsFound = service.getAllCommentsOfUser(idUser);
        assertEquals(0, commentsFound.size());
    }*/

    @Test
    public void SAVE(){
        when(postRepo.findById(idPost)).thenReturn(Optional.of(new Post()));
        when(usersRepo.findById(idUser)).thenReturn(Optional.of(new Users()));
        when(commentRepo.save(comment)).thenReturn(comment);
        boolean result = service.saveComment(comment, idPost, idUser);
        assertTrue(result);
    }

    @Test
    public void SAVE_WITH_NO_POST(){
        when(postRepo.findById(idPost)).thenReturn(Optional.empty());
        when(usersRepo.findById(idUser)).thenReturn(Optional.of(new Users()));
        boolean result = service.saveComment(comment, idPost, idUser);
        assertFalse(result);
    }

    @Test
    public void SAVE_WITH_NO_USER(){
        when(postRepo.findById(idPost)).thenReturn(Optional.of(new Post()));
        when(usersRepo.findById(idUser)).thenReturn(Optional.empty());
        boolean result = service.saveComment(comment, idPost, idUser);
        assertFalse(result);
    }

    @Test
    public void SAVE_WITHOUT_USER_AND_POST(){
        when(postRepo.findById(idPost)).thenReturn(Optional.empty());
        when(usersRepo.findById(idUser)).thenReturn(Optional.empty());
        boolean result = service.saveComment(comment, idPost, idUser);
        assertFalse(result);
    }

    @Test
    public void UPDATE(){
        when(postRepo.findById(idPost)).thenReturn(Optional.of(new Post()));
        when(usersRepo.findById(idUser)).thenReturn(Optional.of(new Users()));
        when(commentRepo.existsById(comment.getId())).thenReturn(true);
        when(commentRepo.save(comment)).thenReturn(comment);
        boolean result = service.updateComment(comment, idPost, idUser, comment.getId());
        assertTrue(result);
    }

    @Test
    public void UPDATE_WITH_NO_POST(){
        when(postRepo.findById(idPost)).thenReturn(Optional.empty());
        when(usersRepo.findById(idUser)).thenReturn(Optional.of(new Users()));
        when(commentRepo.existsById(comment.getId())).thenReturn(true);
        boolean result = service.updateComment(comment, idPost, idUser, comment.getId());
        assertFalse(result);
    }

    @Test
    public void UPDATE_WITH_NO_USER(){
        when(postRepo.findById(idPost)).thenReturn(Optional.of(new Post()));
        when(usersRepo.findById(idUser)).thenReturn(Optional.empty());
        when(commentRepo.existsById(comment.getId())).thenReturn(true);
        boolean result = service.updateComment(comment, idPost, idUser, comment.getId());
        assertFalse(result);
    }

    @Test
    public void UPDATE_WITH_NO_COMMENT(){
        when(postRepo.findById(idPost)).thenReturn(Optional.of(new Post()));
        when(usersRepo.findById(idUser)).thenReturn(Optional.of(new Users()));
        when(commentRepo.existsById(comment.getId())).thenReturn(false);
        boolean result = service.updateComment(comment, idPost, idUser, comment.getId());
        assertFalse(result);
    }

    @Test
    public void UPDATE_WITH_NOTHING(){
        when(postRepo.findById(idPost)).thenReturn(Optional.empty());
        when(usersRepo.findById(idUser)).thenReturn(Optional.empty());
        when(commentRepo.existsById(comment.getId())).thenReturn(false);
        boolean result = service.updateComment(comment, idPost, idUser, comment.getId());
        assertFalse(result);
    }


    @Test
    public void DELETE(){
        when(commentRepo.existsById(comment.getId())).thenReturn(true);
        boolean result = service.deleteComment(comment.getId());
        assertTrue(result);
    }

    @Test
    public void DELETE_WITH_NO_COMMENT(){
        when(commentRepo.existsById(comment.getId())).thenReturn(false);
        boolean result = service.deleteComment(comment.getId());
        assertFalse(result);
    }
}
