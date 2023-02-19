package org.demo.blogapi.commons;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "id")
    Integer id;

    @CreatedDate()
    @Column(name = "created_at",updatable = false)
    Date date;

}
