package com.app.groupprojectapplication.domain.profile;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressSection {
    private Map<String, String> PrimaryAddr;
    private Map<String, String> SecondaryAddr;
}
