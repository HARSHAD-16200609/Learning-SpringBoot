package com.example.demo;

public class Blog {
    private String content;
    private String user;
    private String title;

    public Blog() {}

    public String getContent() { 
        return content; 
    }
    
    public void setContent(String content) { 
        this.content = content; 
    }

    public String getUser() { 
        return user; 
    }
    
    public void setUser(String user) { 
        this.user = user; 
    }

    public String getTitle() { 
        return title; 
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
