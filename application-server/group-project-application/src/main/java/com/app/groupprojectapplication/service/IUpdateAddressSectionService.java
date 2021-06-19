package com.app.groupprojectapplication.service;

public interface IUpdateAddressSectionService {
    boolean updateAdd(String priAdd1, String priAdd2, String priCity,
                         String priState, String priZip, String secAdd1,
                         String secAdd2, String secCity, String secState,
                         String secZip,Integer userId);
}
