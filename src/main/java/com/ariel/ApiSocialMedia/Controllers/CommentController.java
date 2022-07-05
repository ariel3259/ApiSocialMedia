package com.ariel.ApiSocialMedia.Controllers;

import com.ariel.ApiSocialMedia.Dto.CommentDto;
import com.ariel.ApiSocialMedia.Dto.PageCommentsDto;
import com.ariel.ApiSocialMedia.Model.Comment;
import com.ariel.ApiSocialMedia.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

@RestController
@RequestMapping("/api2/comments")
public class CommentController {

    @Autowired
    private CommentService cs;

    @GetMapping("/post/{idPost}")
    public ResponseEntity<PageCommentsDto> getAllCommentsByPost( @PathVariable("idPost") int idPost, @RequestParam("index") int index){
        Page<Comment> rawPageComments = cs.getAllCommentsOfPost(idPost, index);
        PageCommentsDto pageComments = new PageCommentsDto(rawPageComments.getContent(), rawPageComments.getTotalPages(), rawPageComments.getNumber());
        return ResponseEntity.status(200).body(pageComments);
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<PageCommentsDto> getAllCommentsByUser( @PathVariable("idUser") int idUser, @RequestParam("index") int index){
        Page<Comment> rawPageComments = cs.getAllCommentsOfUser(idUser, index);
        PageCommentsDto pageComments = new PageCommentsDto(rawPageComments.getContent(), rawPageComments.getTotalPages(), rawPageComments.getNumber());
        return ResponseEntity.status(200).body(pageComments);
    }

    @PostMapping()
    public ResponseEntity<Boolean> saveComment(@Valid @RequestBody CommentDto request){
        return ResponseEntity.status(201).body(cs.saveComment(request.getComment(), request.getIdPost(), request.getIdUser()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> editComment(@Valid @RequestBody CommentDto request, @PathVariable("id") long id){
        return ResponseEntity.status(201).body(cs.updateComment(request.getComment(), request.getIdPost(), request.getIdUser(), id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidErrors(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;
    }
}
