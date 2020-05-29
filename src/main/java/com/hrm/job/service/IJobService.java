package com.hrm.job.service;

import com.hrm.commons.beans.Job;
import com.hrm.utils.PageModel;

import java.util.List;

public interface IJobService {
    List<Job> findJob(String name, PageModel pageModel);

    int findJobCount(String name);

    Job findJobById(int id);

    int modifyJob(Job job);

    int removeJob(Integer[] ids);

    int addJob(Job job);
}
