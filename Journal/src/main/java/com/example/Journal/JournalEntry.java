package com.example.Journal;

public class JournalEntry {
    private  long id;
    private String content ;


    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return id;
    }
}
