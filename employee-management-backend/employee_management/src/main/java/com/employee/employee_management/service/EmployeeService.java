package com.employee.employee_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.employee_management.entities.Employee;
import com.employee.employee_management.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public ResponseEntity<?> findEmployees() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> createEmployee(Employee emp) {
        return new ResponseEntity<>(employeeRepository.save(emp), HttpStatus.CREATED);
    }

    public ResponseEntity<?> findEmployeeById(Long id) {
        return new ResponseEntity<>(employeeRepository.findById(id).orElse(null), HttpStatus.OK);
    }

    public ResponseEntity<?> updateEmployee(Employee employee, Long id) {
        Employee e = employeeRepository.getReferenceById(id);

        if(e !=null) {
            e.setFirstName(employee.getFirstName());
            e.setEmailId(employee.getEmailId());
            e.setLastName(employee.getLastName());
            ;

            return new ResponseEntity<>(employeeRepository.save(e), HttpStatus.OK);
        }

        return new ResponseEntity<>("No such id found", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>("Deleted SUCCESS", HttpStatus.OK);
    }



}
