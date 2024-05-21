package com.company.project.model.service;

import com.company.project.model.Employee;
import com.company.project.model.Role;
import com.company.project.model.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return (Employee) employeeRepository.findById(id).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        String employeeName = employee.getName();
        if (employeeName == null || employeeName.isEmpty()) {
            throw new IllegalArgumentException("Invalid employee data");
        }
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = (Employee) employeeRepository.findById(id).orElse(null);
        if (existingEmployee != null) {
            String updatedName = updatedEmployee.getName();
            if (updatedName != null && !updatedName.isEmpty()) {
                existingEmployee.setName(updatedName);
            }
            Role updatedRole = updatedEmployee.getRole();
            if (updatedRole != null) {
                existingEmployee.setRole(updatedRole);
            }
            // Alte actualizări de câmpuri după nevoie
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
