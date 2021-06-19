package com.app.groupprojectapplication.service;

public interface IUpdateEmergencyListService {
    boolean updateEmergencyList(String EP1fullName, String EP1phone, String EP1priAdd1, String EP1priAdd2,
                                String EP1priCity, String EP1priState, String EP1priZip,  String EP1secAdd1,
                                String EP1secAdd2, String EP1secCity, String EP1secState, String EP1secZip,
                                String EP2fullName, String EP2phone, String EP2priAdd1, String EP2priAdd2,
                                String EP2priCity, String EP2priState, String EP2priZip, String EP2secAdd1,
                                String EP2secAdd2, String EP2secCity, String EP2secState, String EP2secZip,
                                Integer userId);

}
