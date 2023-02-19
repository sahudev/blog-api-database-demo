package org.demo.blogapi.comments;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import org.demo.blogapi.articles.ArticleEntity;
import org.demo.blogapi.commons.BaseEntity;
import org.demo.blogapi.users.UserEntity;

@Entity(name = "comments")
public class CommentEntity extends BaseEntity {

    String title;
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToOne
    ArticleEntity article;
}
