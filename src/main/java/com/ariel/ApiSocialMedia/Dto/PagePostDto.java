package com.ariel.ApiSocialMedia.Dto;

import java.util.List;

import com.ariel.ApiSocialMedia.Model.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagePostDto {
    private List<Post> posts;
    private int index;
}
