package ro.z2h.service;


import ro.z2h.dao.DepartmentDao;
import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Department;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{

    private final String username = "zth_17";
    private final String password = "passw0rd";

    @Override
    public List<Department> findAllDepartments() {

        DepartmentDao deptDao = new DepartmentDao();

        Connection con = DatabaseManager.getConnection(username, password);
        try {
            return deptDao.getAllDepartments(con);
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
        return new ArrayList<Department>();
    }

    @Override
    public Department findOneDepartment(Long id) {

        DepartmentDao deptDao = new DepartmentDao();

        Connection con = DatabaseManager.getConnection(username, password);

        try {
            return deptDao.getDepartmentById(con, id);
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
}
