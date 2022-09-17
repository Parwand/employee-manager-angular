package net.parwand.employeemanagerangular.infrastructure.web;

import net.parwand.employeemanagerangular.applicationservice.service.EmployeeService;
import net.parwand.employeemanagerangular.domain.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }
    @PostMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }

}
