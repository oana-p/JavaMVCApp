package ro.z2h.service;


import ro.z2h.dao.JobDao;
import ro.z2h.domain.Job;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobServiceImpl implements JobService{

    private final String username = "zth_17";
    private final String password = "passw0rd";

    @Override
    public List<Job> findAllJobs() {

        JobDao jobDao = new JobDao();

        Connection con = DatabaseManager.getConnection(username, password);
        try {
            return jobDao.getAllJobs(con);
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
        return new ArrayList<Job>();
    }

    @Override
    public Job findOneJob(String id) {

        JobDao jobDao = new JobDao();

        Connection con = DatabaseManager.getConnection(username, password);

        try {
            return jobDao.getJobById(con, id);
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
