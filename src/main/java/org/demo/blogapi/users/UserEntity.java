package org.demo.blogapi.users;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import org.demo.blogapi.articles.ArticleEntity;
import org.demo.blogapi.commons.BaseEntity;

import java.util.List;

@Entity(name = "users")
public class UserEntity extends BaseEntity {
    String username;
    String password; //Todo convert into Hash
    String bio;
    String image;

    @ManyToMany(mappedBy = "likedBy")
    List<ArticleEntity> likedArticles;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )
    List<UserEntity>  following;

    @ManyToMany(mappedBy = "following")
    List<UserEntity>  followers;
}
