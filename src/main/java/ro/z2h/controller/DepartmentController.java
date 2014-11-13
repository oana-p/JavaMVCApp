package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;
import ro.z2h.domain.Employee;
import ro.z2h.service.DepartmentServiceImpl;
import ro.z2h.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

@MyController(urlPath = "/department")
public class DepartmentController {

    @MyRequestMethod(urlPath = "/all")
    public List<Department> getAllDepartments() {

        List<Department> allDepartments = (new DepartmentServiceImpl()).findAllDepartments();

       /* Department depart1 = new Department();
        depart1.setId(1L);
        depart1.setDepartmentName("Sales");

        Department depart2 = new Department();
        depart2.setId(2L);
        depart2.setDepartmentName("IT");

        allDepartments.add(depart1);
        allDepartments.add(depart2);
*/
        return allDepartments;
    }
    @MyRequestMethod(urlPath = "/one")
    public Department getOneEmployee(String idDepartment) {

        Department department = new DepartmentServiceImpl().findOneDepartment(Long.valueOf(idDepartment));

        /*employee.setId(new Long(123));
        employee.setLastName("Smith");
        employee.setFirstName("Bob");
*/
        return department;
    }
}
