package com.consumer.Dto;

import java.util.ArrayList;

public class NotifyDTO {
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

    private String productid;
    private ArrayList<String> useridList;
}
