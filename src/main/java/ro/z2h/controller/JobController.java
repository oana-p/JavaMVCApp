package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Job;
import ro.z2h.service.JobServiceImpl;

import java.util.ArrayList;
import java.util.List;

@MyController(urlPath = "/job")
public class JobController {

    @MyRequestMethod(urlPath = "/all")
    public List<Job> getAllJobs() {

        List<Job> allJobs = new JobServiceImpl().findAllJobs();
        return allJobs;
    }

    @MyRequestMethod(urlPath = "/one")
    public Job getOneJob(String idJob) {

        Job job = new JobServiceImpl().findOneJob(idJob);
        return job;
    }
}
