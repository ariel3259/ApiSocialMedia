package com.ariel.ApiSocialMedia.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @OneToOne
    private Users user;
    
    @OneToOne
    private Post post;
    private String message;

    public Comment(String m){
        this.message = m;
    }
}
