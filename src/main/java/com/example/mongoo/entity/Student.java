package com.example.mongoo.entity;


import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.Set;

@Entity("student")
public class Student {
    @Id
    private ObjectId id;

    private String name;

    @Reference
    private Set<Project> projects;

    public Student(String name)
    {
        this.name = name;
    }
    public void setProjects(Set<Project> projects)
    {
        this.projects = projects;
    }

}
