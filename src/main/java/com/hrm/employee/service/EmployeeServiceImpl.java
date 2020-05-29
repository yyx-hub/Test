package com.hrm.employee.service;

import com.hrm.commons.beans.Dept;
import com.hrm.commons.beans.Employee;
import com.hrm.commons.beans.Job;
import com.hrm.employee.dao.IEmployeeDao;
import com.hrm.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Resource
    IEmployeeDao employeeDao;

    @Override
    public List<Job> findAllJob() {
        return employeeDao.selectAllJob();
    }

    @Override
    public List<Dept> findAllDept() {
        return employeeDao.selectAllDept();
    }

    @Override
    public List<Employee> findEmployee( Employee employee, PageModel pageModel) {
        return employeeDao.selectEmployee(employee,pageModel);
    }

    @Override
    public int findEmployeeCount(Employee employee) {
        return employeeDao.selectEmployeeCount(employee);
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeDao.selectEmployeeById(id);
    }

    @Override
    public int modifyEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public int removeEmployee(Integer[] ids) {
        return employeeDao.deleteEmployee(ids);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.insertEmployee(employee);
    }
}
