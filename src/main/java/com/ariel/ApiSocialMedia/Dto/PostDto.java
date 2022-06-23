package com.ariel.ApiSocialMedia.Dto;


import javax.validation.constraints.NotNull;

import com.ariel.ApiSocialMedia.Model.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {

    @NotNull(message = "Title is mandatory")
    private String title;

    @NotNull(message = "Body is mandatory")
    private String body;

    @NotNull(message = "Id user is mandatory")
    private long idUser;

    public Post getPost(){
        return new Post(title, body);
    }
}
