package org.demo.blogapi.comments;

import org.demo.blogapi.articles.ArticleEntity;
import org.demo.blogapi.commons.BaseEntity;
import org.demo.blogapi.users.UserEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "comments")
public class CommentEntity extends BaseEntity {

    String title;
    String body;

    @ManyToOne
    UserEntity author;

    @ManyToOne
    ArticleEntity article;
}
