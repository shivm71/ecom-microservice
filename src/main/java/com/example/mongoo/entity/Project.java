package com.example.mongoo.entity;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity("project")
public class Project {
    @Id
    private ObjectId id;
    private String title;
    private Date publicationDate;
    public Project(String title,Date publicationDate)
    {
        this.title = title;
        this.publicationDate = publicationDate;
    }
}
