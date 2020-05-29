package com.hrm.job.handler;


import com.hrm.commons.beans.Job;
import com.hrm.job.service.IJobService;
import com.hrm.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobHandler {

    @Autowired
    IJobService jobService;
//分页查询所有信息
    @RequestMapping("/findJob.do")
        public String findJob(@RequestParam(defaultValue = "1") int pageIndex, String name, Model model){

        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);

        List<Job> jobs = jobService.findJob(name,pageModel);

        int recordCount = jobService.findJobCount(name);
        pageModel.setRocordCount(recordCount);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("jobs",jobs);
        model.addAttribute("name",name);
        /*for (Job job:jobs){
            System.out.println(job);
        }*/
        return "/jsp/job/job.jsp";
    }

    //按id查找
    @RequestMapping("/findJobById.do")
    public String findJobById(int id,int pageIndex,Model model){
        Job job = jobService.findJobById(id);
        model.addAttribute("job",job);
        model.addAttribute("pageIndex",pageIndex);
        return "/jsp/job/showUpdateJob.jsp";
    }

    //修改查找的内容
    @RequestMapping("/modifyJob.do")
    @ResponseBody
    public String modifyJob(Job job){
        System.out.println(job);
        int rows = jobService.modifyJob(job);
        if (rows > 0){
            return "OK";
        }else {
            return "FAIL";
        }
    }
//删除
    @RequestMapping("/removeJob.do")
    @ResponseBody
    public String removeJob(Integer[] ids){
        try {
             /*for (int id:ids){
            System.out.println(id);
        }*/
            int rows = jobService.removeJob(ids);
            if (rows == ids.length){
                return "OK";
            }else{
                return "FAIL";
            }
        }catch (DataIntegrityViolationException e){
            return "ERROR";
        }

    }

    //添加
    @RequestMapping("/addJob.do")
    @ResponseBody
    public Object addJob(Job job){
        int rows = jobService.addJob(job);
        if (rows > 0){
            PageModel pageModel = new PageModel();
            int recordCount = jobService.findJobCount("null");
            pageModel.setRocordCount(recordCount);

            return pageModel.getTotalSize();
        }else {
            return "FAIL";
        }
    }
}
