package com.hrm.job.service;

import com.hrm.commons.beans.Job;
import com.hrm.job.dao.IJobDao;
import com.hrm.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class JobServiceImpl implements IJobService {

    @Resource
    IJobDao jobDao;
    @Override
    public List<Job> findJob(String name, PageModel pageModel) {
       /* Map map = new HashMap();
        map.put("name",name);
        map.put("pageModel",pageModel);
        return jobDao.selectJob(map);*/
        return jobDao.selectJob(name,pageModel);
    }

    @Override
    public int findJobCount(String name) {
        return jobDao.selectJobCount(name);
    }

    @Override
    public Job findJobById(int id) {
        return jobDao.selectJobById(id);
    }

    @Override
    public int modifyJob(Job job) {
        return jobDao.updateJob(job);
    }

    @Override
    public int removeJob(Integer[] ids) {
        return jobDao.deleteJob(ids);
    }

    @Override
    public int addJob(Job job) {
        return jobDao.insertJob(job);
    }
}
