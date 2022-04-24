package com.ariel.ApiSocialMedia.Controllers;

import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService cs;

    @GetMapping("/post/{idPost}")
    public ResponseEntity<List<Comment>> getAllCommentsByPost(@PathVariable("idPost") int idPost){
        return ResponseEntity.status(200).body(cs.getAllCommentsOfPost(idPost));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Comment>> getAllCommentsByUser(@PathVariable("idUser") int idUser){
        return ResponseEntity.status(200).body(cs.getAllCommentsOfUser(idUser));
    }

    @PostMapping()
    public ResponseEntity<Integer> saveComment(@RequestBody Comment comment){
        return ResponseEntity.status(201).body(cs.saveComment(comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> editComment(@RequestBody Comment comment, @PathVariable("id") int id){
        return ResponseEntity.status(201).body(cs.updateComment(comment, id));
    }
}
