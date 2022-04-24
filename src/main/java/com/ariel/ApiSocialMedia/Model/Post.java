package com.ariel.ApiSocialMedia.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int id;
    private String title;
    private String body;
    private int idUser;
}
