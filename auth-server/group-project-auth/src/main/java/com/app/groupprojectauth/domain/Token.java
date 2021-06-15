package com.app.groupprojectauth.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Token {
    Integer id;
    String content;
    Date validUntil;
    String email;
    Integer creationEmployeeId;
}
