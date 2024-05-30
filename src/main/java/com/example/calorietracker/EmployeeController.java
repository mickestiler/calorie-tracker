/** package com.example.expensetracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("employees")

public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/{id}")

    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {

            return new ResponseEntity<>(employee.get(), HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @PostMapping("")

    public Employee createEmployee(@RequestBody Employee employee) {

        return employeeRepository.save(employee);

    }

    @PutMapping("/{id}")

    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {

            employee.get().setName(employeeDetails.getName());

            employee.get().setAge(employeeDetails.getAge());

            Employee updatedEmployee = employeeRepository.save(employee.get());

            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }



    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {

            employeeRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

}
**/