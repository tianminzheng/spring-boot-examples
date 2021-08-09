package com.example.graphql.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("articles")
public class Article {
    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("minutesRead")
    private Integer minutesRead;

    @Field("authorId")
    private String authorId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMinutesRead() {
        return minutesRead;
    }

    public void setMinutesRead(Integer minutesRead) {
        this.minutesRead = minutesRead;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
