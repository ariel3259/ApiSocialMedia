package com.ariel.ApiSocialMedia.Controllers;

import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService ps;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPost(){
        return ResponseEntity.status(201).body(ps.getPosts());
    }

    @PostMapping
    public ResponseEntity<Integer> savePost(@RequestBody Post post){
        if(post.getTitle().isEmpty() || post.getBody().isEmpty() || post.getIdUser() == 0) return ResponseEntity.status(400).body(4);
        return ResponseEntity.status(201).body(ps.savePost(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updatePost(@RequestBody Post post, @PathVariable("id") int id){
        if(post.getTitle().isEmpty() || post.getBody().isEmpty() || post.getIdUser() == 0 || id == 0)  return ResponseEntity.status(400).body(4);
        return ResponseEntity.status(201).body(ps.updatePost(post, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePost(@PathVariable("id") int id){
        if(id == 0) return ResponseEntity.status(400).body(4);
        return ResponseEntity.status(201).body(ps.deletePost(id));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Post>> getPostsOfUser(@PathVariable("idUser") int idUser){
        if(idUser == 0) return ResponseEntity.status(400).body(null);
        return ResponseEntity.status(201).body(ps.getPostsOfUser(idUser));
    }
}
