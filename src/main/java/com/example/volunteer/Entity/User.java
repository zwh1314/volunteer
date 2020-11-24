package com.example.volunteer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username",nullable = false)
    private String Username;
    @Column(name = "password",nullable = false)
    private String Password;
    @Column(name = "email",nullable = false)
    private String Email;
    @Column(name = "headpicturestr",nullable = false)
    private String HeadPictureStr;
    @Column(name = "usersentence",nullable = false)
    private String UserSentence;


}
