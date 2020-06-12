package com.consumer.service;

import com.consumer.Dao.NotificationDaoImpl;
import com.consumer.Model.UpdateQueueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NotificationService {
    @Autowired
    NotificationDaoImpl notificationDaoImpl;
    void notifyUsers(UpdateQueueRequest updateQueueRequest){
        ArrayList<String> usersidList=notificationDaoImpl.getUsers(updateQueueRequest);
        System.out.println(usersidList);
    }
}
