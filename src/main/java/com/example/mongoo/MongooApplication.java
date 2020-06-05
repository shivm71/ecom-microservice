package com.example.mongoo;

import com.example.mongoo.entity.Project;
import com.example.mongoo.entity.Student;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@SpringBootApplication
public class MongooApplication {

    private static  final String DB_HOST="cluster0-qgcs5.mongodb.net/test?retryWrites=true&w=majority";
    private static final int DB_PORT=27017;
    private static  final String DB_NAME="springg";
    private static  final String DB_USER="test";
    private static  final String DB_PASSWORD="test";




    public static void main(String[] args) {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("com.example.mongoo.entity");
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "springg");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();
        Project project=new Project("MongoDB Morphia",new Date());
        datastore.save(project);
        Student angelika= new Student("angelika");
        angelika.setProjects(Stream.of(project).collect(Collectors.toSet()));
        datastore.save(angelika);
        SpringApplication.run(MongooApplication.class,args);
}

}