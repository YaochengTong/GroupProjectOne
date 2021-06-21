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
    private Integer index;
    private Integer employeeId;
    private String fullName;
    private String SSN;
    private Timestamp startDate;
    private String visaType;
}
