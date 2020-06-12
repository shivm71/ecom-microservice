package com.consumer.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class NotifyDTO {
    private String productid;
    private ArrayList<String> useridList;
}
