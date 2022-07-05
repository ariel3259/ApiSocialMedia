package com.ariel.ApiSocialMedia.Controllers;

import com.ariel.ApiSocialMedia.Dto.PagePostDto;
import com.ariel.ApiSocialMedia.Dto.PostDto;
import com.ariel.ApiSocialMedia.Model.Post;
import com.ariel.ApiSocialMedia.Services.PostService;
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
@RequestMapping("/api2/post")
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping
    public ResponseEntity<PagePostDto> getAllPost(@RequestParam("index") int index){
        Page<Post> rawPagePosts = service.getPosts(index);
        PagePostDto pagePosts = new PagePostDto(rawPagePosts.getContent(), rawPagePosts.getTotalPages(), rawPagePosts.getNumber()); 
    	return ResponseEntity.status(200).body(pagePosts);
    }

    @PostMapping
    public ResponseEntity<String> savePost(@Valid @RequestBody PostDto request){
    	if(!service.savePost(request.getPost(), request.getIdUser())) return ResponseEntity.status(400).body("User doesn't exists");
    	return ResponseEntity.status(200).body("Created post");
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Boolean> deletePost( @PathVariable("id") int id){
        return ResponseEntity.status(201).body(service.deletePost(id));
    }

    @DeleteMapping("/post/user/{idUser}")
    public ResponseEntity<Boolean> deletePostOfUser(@PathVariable("idUser") long idUser){
        return ResponseEntity.status(200).body(service.deletePostOfUser(idUser));
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<PagePostDto> getPostsOfUser( @PathVariable("idUser") long idUser, @RequestParam("index") int index){
    	Page<Post> rawPagePost = service.getPostsOfUser(idUser, index);
        PagePostDto pagePostDto = new PagePostDto(rawPagePost.getContent(), rawPagePost.getTotalPages(), rawPagePost.getNumber());
        return ResponseEntity.status(200).body(pagePostDto);
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
