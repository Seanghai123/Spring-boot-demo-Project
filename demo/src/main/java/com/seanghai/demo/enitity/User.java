package com.seanghai.demo.enitity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "users12")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String password;
    private Integer age;
    private String address;
    private String role ;

    @Column(name = "created_at12")
    private LocalDateTime createdAt;

    @Column (name = "update_at")
    private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public  void preUpdate(){
        this.updatedAt= LocalDateTime.now();
    }
}