package com.example.blogsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "blog title should not be empty")
    @Length(max = 30, message = "blog title length should not be more than 30 characters")
    @Column(columnDefinition = "varchar(30) not null")
    private String title;

    @NotEmpty(message = "blog body should not be empty")
    @Length(max = 200, message = "blog body length should not be more than 200 characters")
    @Column(columnDefinition = "varchar(200) not null")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
