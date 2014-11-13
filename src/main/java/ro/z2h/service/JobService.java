package ro.z2h.service;


import ro.z2h.domain.Job;

import java.util.List;

public interface JobService {
    List<Job> findAllJobs();
    Job findOneJob(String id);
}
