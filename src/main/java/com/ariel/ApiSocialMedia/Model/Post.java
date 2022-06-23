package com.ariel.ApiSocialMedia.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    
    private String title;
    private String body;

    @ManyToOne
    private Users user;
    private Boolean state;

    public Post(String t, String b){
        this.title = t;
        this.body = b;
    }

    @PrePersist
    public void init(){
        state = true;
    }

    @PreUpdate
    public void update(){
        state = true;
    }
}
