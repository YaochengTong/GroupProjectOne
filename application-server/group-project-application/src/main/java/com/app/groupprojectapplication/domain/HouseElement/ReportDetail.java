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
public class ReportDetail {

    private Integer reportDetailId;
    private Integer employeeId;
    private String comments;
    private String createDate;

}
