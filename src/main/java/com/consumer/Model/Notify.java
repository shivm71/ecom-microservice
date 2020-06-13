package com.consumer.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(value="notification")
public class Notify {
    @Id
    private String productid;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public ArrayList<String> getUseridList() {
        return useridList;
    }

    public void setUseridList(ArrayList<String> useridList) {
        this.useridList = useridList;
    }

    private ArrayList<String> useridList;
    public Notify(){}
    public Notify(NotifyQueueRequest notifyQueueRequest){
        this.productid=notifyQueueRequest.getProductid();
        this.useridList.add(notifyQueueRequest.getUserid());
    }
    public void setuseridList(String userid){
        this.useridList.add(userid);
    }
}
