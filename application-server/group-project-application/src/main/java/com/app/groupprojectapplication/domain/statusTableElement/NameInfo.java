package com.app.groupprojectapplication.domain.statusTableElement;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NameInfo {
    private String fullName;
    private String firstName;
    private String lastName;
    private Integer SSN;
    private String email;
    private String title;
}