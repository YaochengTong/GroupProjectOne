package com.app.groupprojectapplication.service;

public interface IUpdateAddressSectionService {
    boolean updatePriAdd(String priAdd1, String priAdd2, String priCity,
                         String priState, String priZip,Integer userId);
    boolean updateSecAdd(String secAdd1, String secAdd2, String secCity,
                          String secState, String secZip,Integer userId);
}
