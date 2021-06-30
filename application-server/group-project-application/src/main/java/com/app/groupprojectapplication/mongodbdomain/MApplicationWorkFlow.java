package com.app.groupprojectapplication.mongodbdomain;

import com.app.groupprojectapplication.domain.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Document("application")
public class MApplicationWorkFlow {
    @Id
    private String id;
    private Timestamp createDate;
    private Timestamp modificationDate;
    private String status;
    private String comments;
    private String type;
    private User user;

}
