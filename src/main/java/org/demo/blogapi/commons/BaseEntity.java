package org.demo.blogapi.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    @CreatedDate()
    @Column(name = "created_at",updatable = false)
    Date date;

}
