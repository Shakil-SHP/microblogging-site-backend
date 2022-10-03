package com.socialmedia.microbloggingsitebackend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username can not be empty or Null.")
    private String username;

    @NotBlank(message = "Password can not be empty or Null.")
    private String password;

    @Email
    @NotBlank(message = "Email can not be empty or Null.")
    private String email;

    private boolean enabled;

    private Instant createdAt;
}
