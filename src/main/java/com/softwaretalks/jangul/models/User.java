package com.softwaretalks.jangul.models;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Entity
@ToString(exclude = "password")
public class User {
    @Id
    private UUID id;
    @Column(unique = true)
    private String email;
    private String password;

    private User() {
        this.id = UUID.randomUUID();
    }

    public User(String email, String password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
