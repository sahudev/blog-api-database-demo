package org.demo.blogapi.users;



import lombok.Getter;
import lombok.Setter;
import org.demo.blogapi.articles.ArticleEntity;
import org.demo.blogapi.commons.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity {
    String username;
    String password; //Todo convert into Hash
    String email;
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
