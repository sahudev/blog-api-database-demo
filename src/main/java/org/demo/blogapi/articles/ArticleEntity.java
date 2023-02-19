package org.demo.blogapi.articles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.demo.blogapi.commons.BaseEntity;
import org.demo.blogapi.users.UserEntity;

import java.util.List;

@Entity(name = "articles")
public class ArticleEntity extends BaseEntity {

    @Column(unique = true,nullable = false, length = 150)
    String slug;
    @Column(nullable = false,length = 200)
    String title;
    String subtitle;
    @Column(nullable = false,length = 8000)
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToMany
    List<UserEntity> likedBy;
}
