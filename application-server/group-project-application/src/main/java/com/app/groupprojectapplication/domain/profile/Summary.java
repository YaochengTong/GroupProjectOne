package com.app.groupprojectapplication.domain.profile;

import com.app.groupprojectapplication.domain.VisaStatus;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Summary {
    private String fullName;
    private String SSN;
    private Timestamp startDate;
    private VisaStatus visaStatus;
}
