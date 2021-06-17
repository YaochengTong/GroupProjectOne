package com.app.groupprojectapplication.domain.statusTableElement;

import lombok.*;
import java.sql.Timestamp;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VisaInfo {
    private String visaType;
    private Timestamp expirationDate;

}
