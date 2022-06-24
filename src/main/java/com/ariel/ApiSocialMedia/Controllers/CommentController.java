package com.ariel.ApiSocialMedia.Controllers;

import com.ariel.ApiSocialMedia.Dto.CommentDto;
import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api2/comments")
public class CommentController {

    @Autowired
    private CommentService cs;

    @GetMapping("/post/{idPost}")
    public ResponseEntity<List<Comment>> getAllCommentsByPost( @PathVariable("idPost") int idPost){
        return ResponseEntity.status(200).body(cs.getAllCommentsOfPost(idPost));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<Comment>> getAllCommentsByUser( @PathVariable("idUser") int idUser){
        return ResponseEntity.status(200).body(cs.getAllCommentsOfUser(idUser));
    }

    @PostMapping()
    public ResponseEntity<Boolean> saveComment(@Valid @RequestBody CommentDto request){
        return ResponseEntity.status(201).body(cs.saveComment(request.getComment(), request.getIdPost(), request.getIdUser()));
    }

    @PutMapping()
    public ResponseEntity<Boolean> editComment(@Valid @RequestBody CommentDto request){
        return ResponseEntity.status(201).body(cs.updateComment(request.getComment(), request.getIdPost(), request.getIdUser()));
    }

}
