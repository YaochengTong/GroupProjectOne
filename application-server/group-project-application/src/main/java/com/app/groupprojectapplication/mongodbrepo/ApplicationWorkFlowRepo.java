package com.app.groupprojectapplication.mongodbrepo;

import com.app.groupprojectapplication.mongodbdomain.MApplicationWorkFlow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ApplicationWorkFlowRepo extends MongoRepository<MApplicationWorkFlow, String> {

}
