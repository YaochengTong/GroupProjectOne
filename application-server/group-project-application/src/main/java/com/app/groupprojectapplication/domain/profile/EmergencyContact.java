package com.app.groupprojectapplication.domain.profile;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmergencyContact {
    private String fullName;
    private String phone;
    private AddressSection address;
}
