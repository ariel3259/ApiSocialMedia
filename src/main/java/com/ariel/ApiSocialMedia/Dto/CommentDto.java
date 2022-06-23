package com.ariel.ApiSocialMedia.Dto;


import javax.validation.constraints.NotNull;
import com.ariel.ApiSocialMedia.Model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {

    @NotNull(message = "Message is mandatory")
    private String message;

    @NotNull(message = "Id user is mandatory")
    private long idUser;

    @NotNull(message = "Id post is mandatory")
    private long idPost;

    public Comment getComment(){
        return new Comment(message);
    }
}
