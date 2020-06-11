package com.example.rawredis.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = QueueRequest.class)
public class QueueRequest{
    public QueueRequest() {}
    public QueueRequest(String productid, String userid) {
        this.productid = productid;
        this.userid = userid;
    }

    private String productid;
    private String userid;

}
