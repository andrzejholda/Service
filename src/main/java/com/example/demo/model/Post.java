package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="post")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @ManyToOne
    private User user;
    @NonNull
    private String body;

}
