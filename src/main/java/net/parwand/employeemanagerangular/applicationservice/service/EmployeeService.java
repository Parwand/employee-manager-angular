package net.parwand.employeemanagerangular.applicationservice.service;

import net.parwand.employeemanagerangular.applicationservice.repository.EmployeeRepository;
import net.parwand.employeemanagerangular.domain.model.Employee;
import net.parwand.employeemanagerangular.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by Id was not found"));
    }

    public void deleteEmployeeById(Long id) {
        employeeRepo.deleteById(id);
    }
}
