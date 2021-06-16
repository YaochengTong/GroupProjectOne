package com.app.groupprojectapplication.domain.profile;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmergencyContactList {
    private EmergencyContact EmergencyPerson1;
    private EmergencyContact EmergencyPerson2;
}
