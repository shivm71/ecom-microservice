package com.consumer.Model;





public class NotifyQueueRequest {
    private int id;
    private String productid;
    private  String userid;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }


    public NotifyQueueRequest() {}
    public NotifyQueueRequest(String productid, String userid) {
        this.productid = productid;this.userid=userid;
    }

}