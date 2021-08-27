package com.springboot.testing.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Document("users")
public class User {
    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("age")
    private Integer age;

    @Field("createAt")
    private Date createdAt;

    @Field("nationality")
    private String nationality;

    @Field("friendsIds")
    private List<String> friendsIds;

    @Field("articlesIds")
    private List<String> articlesIds;

    
    public User() {
		super();
	}

	public User(String id, String name, Integer age, Date createdAt, String nationality, List<String> friendsIds,
			List<String> articlesIds) {
		this(id, name, age, createdAt, nationality);
		this.friendsIds = friendsIds;
		this.articlesIds = articlesIds;
	}
    
	public User(String id, String name, Integer age, Date createdAt, String nationality) {
		super();
		
		Assert.notNull(id, "User Id must not be null");
		Assert.isTrue(name.length() > 5, "User name should be more than 5 characters");
		this.id = id;
		this.name = name;
		this.age = age;
		this.createdAt = createdAt;
		this.nationality = nationality;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<String> getFriendsIds() {
        return friendsIds;
    }

    public void setFriendsIds(List<String> friendsIds) {
        this.friendsIds = friendsIds;
    }

    public List<String> getArticlesIds() {
        return articlesIds;
    }

    public void setArticlesIds(List<String> articlesIds) {
        this.articlesIds = articlesIds;
    }
}
