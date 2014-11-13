package ro.z2h.service;


import ro.z2h.domain.Department;
import ro.z2h.domain.Employee;

import java.util.List;

public interface DepartmentService {

    List<Department> findAllDepartments();
    Department findOneDepartment(Long id);
}
