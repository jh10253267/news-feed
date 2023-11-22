package com.sparta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member extends BaseEntity{
    @Id
    private String username;
    private String password;
}
