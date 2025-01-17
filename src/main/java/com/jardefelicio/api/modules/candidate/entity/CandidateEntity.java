package com.jardefelicio.api.modules.candidate.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidates")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;
    private String name;
    
    @Pattern(regexp = "\\S+).+", message = "O campo [username] não deve contér espaços")
    private String username;

    @Email(message = "O campo [email] deve contér um e-mail válido")
    private String email;

    @Length(min = 8 , max = 100, message = "A senha deve contér entre 8 e 100 caracteres")
    private String password;
    private String description;
    private String curriculum;
    

    @CreationTimestamp
    private LocalDateTime createdAt;
}
