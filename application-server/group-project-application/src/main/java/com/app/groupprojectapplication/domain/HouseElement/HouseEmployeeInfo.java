package com.app.groupprojectapplication.domain.HouseElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HouseEmployeeInfo {

    private Integer employeeId;
    private String employeeName;
    private String employeePhone;
    private String employeeEmail;
    private String employeeCar;

}
