package com.consumer.Model;

import lombok.Getter;
        import lombok.Setter;
        import org.springframework.data.annotation.Id;

@Getter
@Setter


public class NotifyQueueRequest {

    @Id
    private String productid;
    private  String userid;
    public NotifyQueueRequest() {}
    public NotifyQueueRequest(String productid, String userid) {
        this.productid = productid;this.userid=userid;
    }

}