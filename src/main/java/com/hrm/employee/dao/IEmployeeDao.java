package com.hrm.employee.dao;

import com.hrm.commons.beans.Dept;
import com.hrm.commons.beans.Employee;
import com.hrm.commons.beans.Job;
import com.hrm.utils.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEmployeeDao {
    List<Job> selectAllJob();

    List<Dept> selectAllDept();

    List<Employee> selectEmployee(@Param("employee")Employee employee, @Param("pageModel")PageModel pageModel);

    int selectEmployeeCount(Employee employee);

    Employee selectEmployeeById(int id);

    int updateEmployee(Employee employee);

    //响应ids要加上@Param
    int deleteEmployee(@Param("ids") Integer[] ids);

    int insertEmployee(Employee employee);
}
