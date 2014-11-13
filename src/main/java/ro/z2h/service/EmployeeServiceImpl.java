package ro.z2h.service;


import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private final String username = "zth_17";
    private final String password = "passw0rd";

    @Override
    public List<Employee> findAllEmployees() {

        EmployeeDao empDao = new EmployeeDao();

        Connection con = DatabaseManager.getConnection(username, password);
        try {
            return empDao.getAllEmployees(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<Employee>();
    }

    @Override
    public Employee findOneEmployee(Long id) {

        EmployeeDao empDao = new EmployeeDao();

        Connection con = DatabaseManager.getConnection(username, password);

        try {
            return empDao.getEmployeeById(con, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public void deleteOneEmployee(Long id) {

        EmployeeDao empDao = new EmployeeDao();

        Connection con = DatabaseManager.getConnection(username, password);


        empDao.deleteEmployee(findOneEmployee(id), con);

        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
