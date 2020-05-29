package com.hrm.employee.handler;


import com.hrm.commons.beans.Dept;
import com.hrm.commons.beans.Employee;
import com.hrm.commons.beans.Job;
import com.hrm.employee.service.IEmployeeService;
import com.hrm.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeHandler {

    @Autowired
    IEmployeeService employeeService;

    @RequestMapping("/findEmployee.do")
    public String findEmployee(@RequestParam(defaultValue = "1") int pageIndex,Integer dept_id,Integer job_id ,Employee employee, Model model){

        if (job_id != null){
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if (dept_id !=null){
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
        System.out.println("搜索条件："+employee);
       //职位下拉列表信息
        List<Job> jobs = employeeService.findAllJob();
        //部门下拉列表信息
        List<Dept> depts = employeeService.findAllDept();
        //记录数
        int recordCount = employeeService.findEmployeeCount(employee);
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(2);
        pageModel.setRocordCount(recordCount);
        //员工分页查询和搜索
        List<Employee> employees = employeeService.findEmployee(employee,pageModel);
        /*for (Employee e :employees){
            System.out.println(e);
        }*/
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        model.addAttribute("employees",employees);
        model.addAttribute("employee",employee);
        return "/jsp/employee/employee.jsp";
    }

    //员工修改-按id查询要修改的员工
    @RequestMapping("/findEmployeeById.do")
    public String findEmployeeById(int id,int pageIndex,Model model){
        //因为修改的时候仍然要查询所有的dept和job
        List<Job> jobs = employeeService.findAllJob();
        List<Dept> depts = employeeService.findAllDept();
        Employee employee = employeeService.findEmployeeById(id);
        model.addAttribute("employee",employee);
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        model.addAttribute("pageIndex",pageIndex);
        return "/jsp/employee/showUpdateEmployee.jsp";
    }
    //员工信息修改
    @RequestMapping("/modifyEmployee.do")
    //ajax请求需要下面的注解
    @ResponseBody
    public String modifyEmployee(Employee employee){
       // System.out.println("修改的员工信息"+employee);
        int rows = employeeService.modifyEmployee(employee);
        if(rows > 0){
            return "OK";
        }else{
            return "FAIL";
        }
    }

    //员工删除
    @RequestMapping("/removeEmployee.do")
    @ResponseBody
    public String removeEmployee(Integer[] ids){
        int rows = employeeService.removeEmployee(ids);
        if (rows == ids.length){
            return "OK";
        }else{
            return "FAIL";
        }
        /*for (int id:ids){
            System.out.println(id);
        }*/

    }

    //转到添加员工页面里为dept和job赋所有值
    @RequestMapping("toAddEmployee.do")
    public String toAddEmployee(Model model){
        List<Job> jobs = employeeService.findAllJob();
        List<Dept> depts = employeeService.findAllDept();
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        return "/jsp/employee/showAddEmployee.jsp";
    }
    //添加员工信息
    @RequestMapping("addEmployee.do")
    @ResponseBody
    public Object addEmployee(Employee employee){
       // System.out.println("添加员工信息："+employee);
        int rows = employeeService.addEmployee(employee);
        if(rows > 0){
            int recordCount = employeeService.findEmployeeCount(null);
            PageModel pageModel = new PageModel();
            pageModel.setRocordCount(recordCount);
            pageModel.setPageSize(2);
            //返回为整型，就要把字符串类型的String改为Object
            int totalSize = pageModel.getTotalSize();
            System.out.println(totalSize);
            return totalSize;
        }else {
            return "FAIL";
        }
    }

}
