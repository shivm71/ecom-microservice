package com.example.rawredis.Dao;


import com.example.rawredis.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDao extends MongoRepository<User,Long> {

    User findByUsername(String username);

}
