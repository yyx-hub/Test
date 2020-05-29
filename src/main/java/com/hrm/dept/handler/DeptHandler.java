package com.hrm.dept.handler;


import com.hrm.commons.beans.Dept;
import com.hrm.dept.service.IDeptService;
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
@RequestMapping("/dept")
public class DeptHandler {
    @Autowired
    IDeptService deptService;

    @RequestMapping("/findDept.do")
    public String findDept(@RequestParam(defaultValue = "1") int pageIndex, String name, Model model){

        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<Dept> depts = deptService.findDept(name,pageModel);

        //查询部门记录数
        int recordCount = deptService.findDeptCount(name);
        pageModel.setRocordCount(recordCount);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("depts",depts);
        model.addAttribute("name",name);
        for (Dept d:depts){
            System.out.println(d);
        }
        return "/jsp/dept/dept.jsp";
    }
    //查找部门修改的信息
    @RequestMapping("/findDeptById.do")
    public String findDeptById(int id,int pageIndex,Model model){
        Dept dept = deptService.findDeptById(id);
        model.addAttribute("dept",dept);
        model.addAttribute("pageIndex",pageIndex);
        return "/jsp/dept/showUpdateDept.jsp";
    }

    //部门修改
    @RequestMapping("/modifyDept.do")
    @ResponseBody  //返回数据作为响应体中的数据，而不是对映的视图地址
    public String modifyDept(Dept dept){
        System.out.println(dept);
        int rows = deptService.modifyDept(dept);
        if (rows>0){
            return "OK";
        }else{
            return "FAIL";
        }
    }
//部门删除
    @RequestMapping("/removeDept.do")
    @ResponseBody
    public String removeDept(Integer[] ids){
        try {
            int rows = deptService.removeDept(ids);
            if (rows == ids.length){
                return "OK";
            }else{
                return "FAIL";
            }
        }catch (DataIntegrityViolationException e){
            return "ERROR";
        }

    }
    //部门增加
    @RequestMapping("/addDept.do")
    @ResponseBody
    public Object addDept(Dept dept){
        int rows = deptService.addDept(dept);
        if(rows > 0){
            int recordCount = deptService.findDeptCount(null);
            PageModel pageModel = new PageModel();
            pageModel.setRocordCount(recordCount);
            int totalSize = pageModel.getTotalSize();
            System.out.println(totalSize);
            return totalSize;
        }else{
            return "FAIL";
        }
    }
}
