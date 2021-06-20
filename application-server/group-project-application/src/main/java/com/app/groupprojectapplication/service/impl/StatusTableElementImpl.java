package com.app.groupprojectapplication.service.impl;

import com.app.groupprojectapplication.dao.IApplicationWorkFlowDao;
import com.app.groupprojectapplication.dao.IEmployeeDao;
import com.app.groupprojectapplication.dao.IUserDao;
import com.app.groupprojectapplication.dao.IVisaStatusDao;
import com.app.groupprojectapplication.domain.Employee;
import com.app.groupprojectapplication.domain.Person;
import com.app.groupprojectapplication.domain.statusTableElement.NameInfo;
import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;
import com.app.groupprojectapplication.domain.statusTableElement.StatusTableElement;
import com.app.groupprojectapplication.domain.statusTableElement.VisaInfo;
import com.app.groupprojectapplication.service.IStatusTableElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StatusTableElementImpl implements IStatusTableElementService {

    private StatusTableElement statusTableElement;
    private final String applicationType = "visa status application";

    @Autowired
    IEmployeeDao iEmployeeDao;

    @Autowired
    IUserDao iUserDao;

    @Autowired
    IVisaStatusDao iVisaStatusDao;

    @Autowired
    IApplicationWorkFlowDao iApplicationWorkFlowDao;

    @Override
    @Transactional
    public List<StatusTableElement> getStatus() {
         List<StatusTableElement> statusList = new ArrayList<>();

//         List<Employee> employeeList = new ArrayList<>();
        List<Integer> testIds = Arrays.asList(89, 556, 557, 558);
         for (Integer i : testIds) {
             StatusTableElement ste = getStatusByEmployeeId(i);
             // filter out the status that doesnt need further action
             if (!ste.getVisaInfo().getNextStep().equals("No Info") &&
                     !ste.getVisaInfo().getNextStep().equals("No Action") )
             statusList.add(ste);
         }

         return statusList;
    }

    @Override
    @Transactional
    public StatusTableElement getStatusByEmployeeId(Integer userId) {
        Integer employeeId = iUserDao.getEmployeeIdByUserId(userId);
        Employee employee = iEmployeeDao.getEmployeeById(employeeId);
        return getStatusByEmployee(employee);
    }


    private StatusTableElement getStatusByEmployee(Employee employee) {
        Integer employeeId = employee.getId();
        Integer userId = iEmployeeDao.getUserIdByEmployeeId(employeeId);
        statusTableElement = new StatusTableElement();
        Person person = employee.getPerson();
        statusTableElement.setEmployeeId(employee.getId());
        statusTableElement.setNameInfo(setNameInfo(employee, person));
        statusTableElement.setVisaInfo(setVisaInfo(userId, employee));

        return statusTableElement;
    }

    private NameInfo setNameInfo(Employee employee, Person person) {
        NameInfo nameInfo = new NameInfo();
        nameInfo.setFirstName(person.getFirstName());
        nameInfo.setLastName(person.getLastName());
        nameInfo.setFullName(getFullName(person));
        nameInfo.setSSN(Integer.parseInt(person.getSsn()));
        nameInfo.setEmail(person.getEmail());
        nameInfo.setTitle(employee.getTitle());

        return nameInfo;
    }

    public String getFullName(Person person) {
        String fullName;
        if (person.getMiddleName() != null && person.getMiddleName().length() > 0){
            fullName = person.getFirstName() + " " + person.getMiddleName() + " " + person.getLastName();
        } else {
            fullName = person.getFirstName() + " " + person.getLastName();
        }
        return  fullName;
    }


    public String determineNextStep(String currentStep) {
        String nextStep;
        switch (currentStep) {
            case "OPT Receipt": nextStep = "OPT EAD"; break;
            case "OPT EAD": nextStep = "I-983 for OPT STEM"; break;
            case "I-983 Submitted": nextStep = "I-20 after I-983 Submitted"; break;
            case "OPT STEM Receipt": nextStep = "OPT STEP EAD"; break;
            case "OPT STEM EAD": nextStep="No Action"; break;
            default:
//                throw new IllegalStateException("Unexpected value: " + currentStep);
                nextStep = "OPT Receipt";
        }
        return nextStep;
    }
    public VisaInfo setVisaInfo(Integer userId, Employee employee) {
        VisaInfo visaInfo = new VisaInfo();
        visaInfo.setVisaType(employee.getVisaStatus().getVisaType());
        visaInfo.setExpirationDate(employee.getVisaEndDate());
        visaInfo.setDayLeft(iVisaStatusDao.getVisaAuthorizationLeftDay(employee.getId()));
        visaInfo.setNextStep(determineNextStep(iApplicationWorkFlowDao.getApplicationWorkFlowByUserIdAndApplicationType(userId, applicationType).get(0).getStatus()));
        return visaInfo;
    }
}
