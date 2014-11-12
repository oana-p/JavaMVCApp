package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;

@MyController(urlPath = "/employee")
public class EmployeeController {

   @MyRequestMethod(urlPath = "/all")
   public String getAllEmployees() {

       String allEmployees = "all employees";

       return allEmployees;
   }

    @MyRequestMethod(urlPath = "/one")
    public String getOneEmployee() {

        String employee = "oneRandomEmployee";

        return employee;
    }
}
