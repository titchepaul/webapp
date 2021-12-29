package com.classrooms.webapp.service;

import com.classrooms.webapp.model.Employee;
import com.classrooms.webapp.repository.EmployeeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(Long id){
        return  employeeProxy.getEmployee(id);
    }
    public Iterable<Employee> getEmployees(){
        return employeeProxy.getEmployees();
    }
    public void deleteEmployee(Long id){
        employeeProxy.deleteEmployee(id);
    }
    public Employee saveEmployee(Employee e){

        e.setLastName(e.getLastName().toUpperCase());
        Employee employeeSaved;
        if(e.getId() == null){
            employeeSaved = employeeProxy.createEmployee(e);
        }else{
            employeeSaved = employeeProxy.updateEmployee(e);
        }
        return  employeeSaved;
    }

}
