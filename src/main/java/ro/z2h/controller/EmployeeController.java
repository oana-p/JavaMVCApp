package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

@MyController(urlPath = "/employee")
public class EmployeeController {

   @MyRequestMethod(urlPath = "/all")
   public List<Employee> getAllEmployees() {

       List<Employee> allEmployees = new EmployeeServiceImpl().findAllEmployees();

       /*Employee employee1 = new Employee();

       employee1.setId(new Long(123));
       employee1.setLastName("Smith");
       employee1.setFirstName("Bob");

       Employee employee2 = new Employee();

       employee2.setId(new Long(13));
       employee2.setLastName("Smith");
       employee2.setFirstName("Bob");

       Employee employee3 = new Employee();

       employee3.setId(new Long(12));
       employee3.setLastName("Smith");
       employee3.setFirstName("Bob");

       allEmployees.add(employee1);
       allEmployees.add(employee2);
       allEmployees.add(employee3);*/

       return allEmployees;
   }

    @MyRequestMethod(urlPath = "/one")
    public Employee getOneEmployee(String idEmployee) {

        Employee employee = new EmployeeServiceImpl().findOneEmployee(Long.valueOf(idEmployee));

        /*employee.setId(new Long(123));
        employee.setLastName("Smith");
        employee.setFirstName("Bob");
*/
        return employee;
    }
    @MyRequestMethod(urlPath = "/delete")
    public void deleteOneEmployee(String idEmployee) {

        new EmployeeServiceImpl().deleteOneEmployee(Long.valueOf(idEmployee));
    }
}
