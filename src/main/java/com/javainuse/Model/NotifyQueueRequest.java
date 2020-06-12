package com.javainuse.Model;

        import com.fasterxml.jackson.annotation.JsonIdentityInfo;
        import com.fasterxml.jackson.annotation.ObjectIdGenerators;
        import lombok.Getter;
        import lombok.Setter;
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = NotifyQueueRequest.class)
public class NotifyQueueRequest {
    public NotifyQueueRequest() {}
    public NotifyQueueRequest(String productid, String userid) {
        this.productid = productid;
    }

    private String productid;

}