package com.consumer.Dao;

import com.consumer.Model.Notify;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends MongoRepository<Notify, String> {



}
