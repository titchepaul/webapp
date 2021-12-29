package com.classrooms.webapp.controller;

import com.classrooms.webapp.model.Employee;
import com.classrooms.webapp.service.EmployeeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Employee> listEmployees = employeeService.getEmployees();
        model.addAttribute("employees",listEmployees);
        return "home";
    }

    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final long id) {
        employeeService.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        if(employee.getId() != null) {
            // Employee from update form has the password field not filled,
            // so we fill it with the current password.
            Employee current = employeeService.getEmployee((long)employee.getId());
            employee.setPassword(current.getPassword());
        }
        employeeService.saveEmployee(employee);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/createEmployee")
    public String createEmployee(Model model) {
        Employee e = new Employee();
        model.addAttribute("employee", e);
        return "formNewEmployee";
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") final int id, Model model) {
        Employee e = employeeService.getEmployee((long)id);
        model.addAttribute("employee", e);
        return "formUpdateEmployee";
    }
}
