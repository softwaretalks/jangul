package com.softwaretalks.jangul.models;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@Entity
@ToString(exclude = "password")
public class User {
    @Id
    private UUID id;
    @Column(unique = true)
    @Email(message = "email should be a valid email address")
    private String email;

    @NotNull(message = "password can not be null")
    @Pattern(regexp = "^.{5,100}$", message = "password length must be between 5 and 100")
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
