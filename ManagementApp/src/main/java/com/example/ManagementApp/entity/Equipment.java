package com.example.ManagementApp.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Equipment {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String name;

// Getters and setters (constructors, toString, etc. can also be added as needed)

public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}
}
