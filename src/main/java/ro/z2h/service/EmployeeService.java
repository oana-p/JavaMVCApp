package ro.z2h.service;


import ro.z2h.domain.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();
    Employee findOneEmployee(Long id);
    void deleteOneEmployee(Long id);
}
