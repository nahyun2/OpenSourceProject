package com.example.exerciseboard.model;

import jakarta.persistence.*;

@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String category;
    
    private String author;
    
    private String title;
    
    private String content;
    
    private String location;
    
    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
        
    public String getCategory() {
        return category;
    }
        
    public void setCategory(String category) {
        this.category = category;
    }
        
    public String getAuthor() {
        return author;
    }
        
    public void setAuthor(String author) {
        this.author = author;
    }
        
    public String getTitle() {
        return title;
    }
        
    public void setTitle(String title) {
        this.title = title;
    }
        
    public String getContent() {
        return content;
    }
        
    public void setContent(String content) {
        this.content = content;
    }
        
    public String getLocation() {
        return location;
    }
        
    public void setLocation(String location) {
        this.location = location;
    }
}
