package com.hrm.employee.service;

import com.hrm.commons.beans.Dept;
import com.hrm.commons.beans.Employee;
import com.hrm.commons.beans.Job;
import com.hrm.utils.PageModel;

import java.util.List;

public interface IEmployeeService {
    List<Job> findAllJob();

    List<Dept> findAllDept();
    //涉及到关联属性 所以使用xml写sql语句更方便一点
    List<Employee> findEmployee(Employee employee, PageModel pageModel);

    int findEmployeeCount(Employee employee);

    Employee findEmployeeById(int id);

    int modifyEmployee(Employee employee);

    int removeEmployee(Integer[] ids);

    int addEmployee(Employee employee);
}
