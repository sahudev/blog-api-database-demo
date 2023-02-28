package org.demo.blogapi.articles;


import org.demo.blogapi.commons.BaseEntity;
import org.demo.blogapi.users.UserEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
