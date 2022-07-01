package com.ariel.ApiSocialMedia.Dto;

import java.util.List;

import com.ariel.ApiSocialMedia.Model.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageCommentsDto {
    private List<Comment> comments;
    private int index;
}
